package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClienteDTO;
import model.TipoUsuarioDTO;
import model.UsuarioDTO;

import java.io.IOException;
import java.util.List;

import dao.DAOFactory;
import dao.ClienteDAO;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/clienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String opcion=request.getParameter("opcion");
		System.out.println("get opcion --->" +opcion);
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");
		System.out.println("Valor del boton :" + opcion);
		
		switch (opcion) {
		case "reg": 
					registrar(request, response);
					break;
		case "act":
					actualizar(request,response); 
					break;			
		case "lis":
					listar(request,response); 
					break;
		case "nue":
					nuevo(request,response); 
					break;
		case "bus":
					buscar(request,response); 
					break;
		case "eli":
					eliminar(request,response); 
					break;
					
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}		
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variables
		String mensaje="";
		
		//Entradas
		int codigo=  Integer.parseInt(request.getParameter("cod"));
	  
		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO dao= fabrica.getClienteDAO();
	
		int ok=dao.eliminar(codigo);
	
		if(ok==0) {
			mensaje+=" <script> alert('"+" Error al eliminar los datos" +"') </script>";
			 
		}else {
			mensaje+=" <script> alert('"+"Eliminación del cliente  "+codigo+" OK" +"') </script>";
		 
		}
		
		//Salida
		request.setAttribute("mensaje", mensaje);
		
		//Invocamos a Listar
		listar(request,response);
		
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Entradas
		 int codigo= Integer.parseInt(  request.getParameter("cod"));
		 
		 //Procesos
		 DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		 ClienteDAO dao= fabrica.getClienteDAO();
		 ClienteDTO cliente= dao.obtenerCliente(codigo);
		 
		 //Salidas
		 request.setAttribute("cliente", cliente);
		 request.getRequestDispatcher("cliente/actualizarCliente.jsp").forward(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("cliente/registrarCliente.jsp").forward(request, response);
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO dao= fabrica.getClienteDAO();
		List<ClienteDTO> lista= dao.listar(); System.out.println(lista.size());
		//Salida
		request.setAttribute("lstClientes", lista);
		request.getRequestDispatcher("cliente/listarClientes.jsp").forward(request, response);
		
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variables
		String mensaje="";
 
		//entradas
		int codigo = Integer.parseInt(request.getParameter("txtCodigo"));
		String nombre= request.getParameter("txtNombres");
		String apePaterno = request.getParameter("txtApePat");
		String apeMaterno= request.getParameter("txtApeMat");
		String email=request.getParameter("txtEmail");
		long celular =Long.parseLong(request.getParameter("txtNumCel"));
		String userName=request.getParameter("txtUserName");
		String contrasena=request.getParameter("txtPassword");
		int codTipoUsuario= Integer.parseInt(request.getParameter("cboTipoUsuario"));
		 
		  
		//Enviando las entradas al constructor
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsername(userName);
		usuarioDTO.setContrasena(contrasena);
		
			TipoUsuarioDTO tipoUsuarioDTO= new TipoUsuarioDTO();
			tipoUsuarioDTO.setCodTipoUsuario(codTipoUsuario); 
		 
		usuarioDTO.setTipoUsuario(tipoUsuarioDTO);
		
		//Contructor con  7 parámetros
		ClienteDTO objCliente= new ClienteDTO(codigo, nombre, apePaterno, apeMaterno, email, celular,  usuarioDTO);
	  
		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO dao= fabrica.getClienteDAO();
		 
		//pasamos el objeto a registrar
		int ok= dao.actualizar(objCliente);
		
		if(ok == 0) {
					mensaje+=" <script> alert('"+" Error al registrar los datos" +"')</script>";
		}else {
					mensaje+=" <script> alert('"+" Actualización exitosa" +"')</script>";
			}
				
		
		request.setAttribute("mensaje", mensaje);
		
		//invocar a listar 
		listar(request, response);
		
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//variables 
		String mensaje="";
		
		//entradas
		String nombre= request.getParameter("txtNombres");
		String apePaterno = request.getParameter("txtApePat");
		String apeMaterno= request.getParameter("txtApeMat");
		String email=request.getParameter("txtEmail");
		long celular =Long.parseLong(request.getParameter("txtNumCel"));
		String userName=request.getParameter("txtUserName");
		String contrasena=request.getParameter("txtPassword");
		int codTipoUsuario= Integer.parseInt(request.getParameter("cboTipoUsuario"));
		
		
		///Enviando las entradas al constructor
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsername(userName);
		usuarioDTO.setContrasena(contrasena);
		
			TipoUsuarioDTO tipoUsuarioDTO= new TipoUsuarioDTO();
			tipoUsuarioDTO.setCodTipoUsuario(codTipoUsuario); 
		 
		usuarioDTO.setTipoUsuario(tipoUsuarioDTO);
		
		//Contructor con  6 parámetros
		 ClienteDTO objCliente= new ClienteDTO(nombre, apePaterno, apeMaterno, email, celular, usuarioDTO); 
		 
		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO dao= fabrica.getClienteDAO();
		 
		//pasamos el objeto a registrar
		int ok= dao.registrar(objCliente);
		
		if(ok == 0) {
					mensaje+=" <script> alert('"+" Error al registrar los datos" +"')</script>";
		}else {
					mensaje+=" <script> alert('"+" Registro exitoso" +"')</script>";
			}
				
		request.setAttribute("mensaje", mensaje);
		
		//invocar a listar 
		listar(request, response);
		
	}
}
