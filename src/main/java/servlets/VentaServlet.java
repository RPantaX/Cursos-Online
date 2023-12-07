package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CarritoDTO;
import model.CursoDTO;
import model.VentaDTO;
import model.VentaDetalleDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.CursoDAO;
import dao.DAOFactory;
import dao.VentaDAO;

/**
 * Servlet implementation class VentaServlet
 */
@WebServlet("/ventaServlet")
public class VentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//Obtenemos la fabrica DAO
	DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	CursoDAO dao=fabrica.getCursoDAO();
	
	//Establecemos variables globales
	List<CarritoDTO> listaCarrito= new ArrayList<CarritoDTO>();
	int item=0;
	double totalPagar=0.0;
	int cantidad=1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");
		System.out.println("opcion --> :" + opcion);
		
		switch (opcion) {
		case "lis": 
					listar(request, response);
					break;
		case "addCar":
					agregarCarrito(request,response); 
					break;			
		case "carrito":
					carrito(request,response); 
					break;
		case "eliCar":
					eliminar(request,response); 
					break;
		case "actCar":
					actualizarCarro(request,response); 
					break;
		case "generarVenta":
					generarVenta(request,response); 
					break;
					
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}		
	}

	private void generarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idCliente= Integer.parseInt(request.getParameter("idCliente"));
		
		//cargar datos a la cabecera
		VentaDTO cabVentaDTO= new VentaDTO();
		cabVentaDTO.setCodCliente(idCliente);
		cabVentaDTO.setTotal(totalPagar);
		
		//cargar datos al detalle
		List<VentaDetalleDTO> listaDetalle= new ArrayList<VentaDetalleDTO>();
		
		for(CarritoDTO carritoDTO : listaCarrito) {
			VentaDetalleDTO detVentaDTO = new VentaDetalleDTO();
			detVentaDTO.setCantidad(carritoDTO.getCantidad());;
			detVentaDTO.setCodCurso(carritoDTO.getCodCurso());
			detVentaDTO.setPrecio(carritoDTO.getPrecioCompra());
			listaDetalle.add(detVentaDTO);
		}
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		VentaDAO dao= fabrica.getVentaDAO();
		
		dao.registrarVenta(cabVentaDTO, listaDetalle);
		
		//INICIALIZAMOS LAS VARIABLES
		listaCarrito= new ArrayList<CarritoDTO>();
		item=0;
		totalPagar=0.0;
		cantidad=1;
		
		request.getRequestDispatcher("venta/historialCursos.jsp").forward(request, response);
	}

	private void actualizarCarro(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//Entradas
		int codigo=Integer.parseInt(request.getParameter("cod"));
		int cantidad=Integer.parseInt(request.getParameter("cantidad"));
		
		for(int i=0; i<listaCarrito.size(); i++) {
			if(listaCarrito.get(i).getCodCurso()==codigo) {
				listaCarrito.get(i).setCantidad(cantidad);
				double st=listaCarrito.get(i).getPrecioCompra()*cantidad;
				listaCarrito.get(i).setSubTotal(st);
			}
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variable
		String mensaje="";
		String url;
		
		//Entradas
		int codigo=Integer.parseInt(request.getParameter("cod"));
		for(int i=0; i<listaCarrito.size(); i++) {
			if(listaCarrito.get(i).getCodCurso()==codigo) {
				listaCarrito.remove(i);
			}
		}
			carrito(request,response);
	}

	private void carrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		totalPagar=0.0;
		//salida
		request.setAttribute("listaCarrito", listaCarrito);
		
		for(int i=0; i<listaCarrito.size(); i++) {
			totalPagar=totalPagar+listaCarrito.get(i).getSubTotal();
		}
		request.setAttribute("totalPagar", totalPagar);
		request.getRequestDispatcher("venta/carritoCompra.jsp").forward(request, response);
	}

	private void agregarCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//entradas
		int codigoCurso=Integer.parseInt(request.getParameter("cod"));
		
		//obtener información del curso
		CursoDTO curso = dao.obtenerCurso(codigoCurso);
		
		//Verificar si el curso ya está en el carrito
		int posicionEnCarrito = buscarCursoEnCarrito(codigoCurso);
		
		if(posicionEnCarrito != -1) {
			//El curso ya está en el carrito, actualizar la cantidad y el subtotal
			actualizarCursoExistenteEnCarrito(posicionEnCarrito, curso);
		} else {
			//El curso no está en el carrito, agregar uno nuevo
			agregarNuevoCursoAlCarrito(curso);
			
		}
		
		//Actualizar la lista de cursos disponibles
		List<CursoDTO> listaCursos=dao.listar();
		request.setAttribute("lstCursos", listaCursos);
		
		//Actualizar la cantidad de elementos en el carrito
		request.setAttribute("contador", listaCarrito.size());
		
		//Redirigir a la página de carrito
		request.getRequestDispatcher("venta/cardsCursos.jsp").forward(request, response);
		}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obtenemos la fabrica DAO
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		CursoDAO dao= fabrica.getCursoDAO();
		
		List<CursoDTO> lista= dao.listar();
		
		//Salidas
		request.setAttribute("lstCursos", lista);
		request.getRequestDispatcher("venta/cardsCursos.jsp").forward(request, response);
	}
	
	/*MÉTODOS QUE AYUDARÁN AL MÉTODO AgregarCarrito*/
	private int buscarCursoEnCarrito(int codigoCurso) {
		for(int i=0; i<listaCarrito.size(); i++) {
			if(codigoCurso == listaCarrito.get(i).getCodCurso()) {
				return i;
			}
		}
		return -1;
	}
	private void actualizarCursoExistenteEnCarrito(int posicion, CursoDTO curso) {
		CarritoDTO carritoActual=listaCarrito.get(posicion);
		int nuevaCantidad=carritoActual.getCantidad()+1;
		double nuevoSubtotal=nuevaCantidad*curso.getPrecio();
		
		carritoActual.setCantidad(nuevaCantidad);
		carritoActual.setSubTotal(nuevoSubtotal);
	}
	private void agregarNuevoCursoAlCarrito(CursoDTO curso) {
		item++;
		CarritoDTO nuevoCursoEnCarrito = new CarritoDTO();
		nuevoCursoEnCarrito.setItem(item);
		nuevoCursoEnCarrito.setCodCurso(curso.getCodigo());
		nuevoCursoEnCarrito.setPrecioCompra(curso.getPrecio());
		nuevoCursoEnCarrito.setCantidad(1);
		nuevoCursoEnCarrito.setSubTotal(curso.getPrecio());
		listaCarrito.add(nuevoCursoEnCarrito);
	}

}
