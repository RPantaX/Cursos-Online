package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOFactory;
import dao.DocenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 

import model.DocenteDTO;
import model.ProfesionDTO;

@WebServlet("/docenteServlet")
public class DocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocenteServlet() {
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
		DocenteDAO dao= fabrica.getDocenteDAO();
		
		int ok=dao.eliminar(codigo);
		
		if(ok==0) {
			mensaje+=" <script> alert('"+" Error al eliminar los datos" +"') </script>";
			 
		}else {
			mensaje+=" <script> alert('"+"Eliminación del Docente  "+codigo+" OK" +"') </script>";
		 
		}
		
		//Salida
		request.setAttribute("mensaje", mensaje);
		
		//Invocamos a Listar
		listar(request,response);
		
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Entradas
		 int codigo= Integer.parseInt(  request.getParameter("cod")  );
		 
		 
		 //Procesos
		 DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		 DocenteDAO dao= fabrica.getDocenteDAO();
		 
		 DocenteDTO docente= dao.obtenerDocente(codigo);
		 
		 //Salidas
		 request.setAttribute("docente", docente);
		 request.getRequestDispatcher("docente/actualizarDocente.jsp").forward(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("docente/registrarDocente.jsp").forward(request, response);
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		DocenteDAO dao= fabrica.getDocenteDAO();
		
		List<DocenteDTO> lista = dao.listar();
		
		//Salida
		request.setAttribute("lstDocentes", lista);
		request.getRequestDispatcher("docente/listarDocentes.jsp").forward(request, response);
		
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
		int idProfesion= Integer.parseInt(request.getParameter("cboProfesion"));
		  
		//Enviando las entradas al constructor
		ProfesionDTO objProfesion = new ProfesionDTO();
		objProfesion.setCodigo(idProfesion);
		
		//Contructor con  7 parámetros
		DocenteDTO objDocente = new DocenteDTO( codigo,nombre,apePaterno,apeMaterno, email , celular, objProfesion);
	  
		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		DocenteDAO dao= fabrica.getDocenteDAO();
		
		int ok= dao.actualizar(objDocente);
		
		if(ok==0) {
			mensaje+=" <script> alert('"+" Error al actualizar los datos" +"') </script>";
			 
		}else {
			mensaje+=" <script> alert('"+"Actualización exitosa" +"') </script>";		 
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
		int idProfesion= Integer.parseInt(request.getParameter("cboProfesion"));
		
		
		//Enviando las entradas al constructor
		ProfesionDTO objProfesion = new ProfesionDTO();
		objProfesion.setCodigo(idProfesion);
		
		//crear el objeto Docente
		DocenteDTO objDocente = new DocenteDTO(nombre, apePaterno, apeMaterno, email, celular, objProfesion);
		
		
		//proceso de registro  
		
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		DocenteDAO dao= fabrica.getDocenteDAO();
		
		//pasamos el objeto a registrar
		int ok= dao.registrar(objDocente);
		
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
