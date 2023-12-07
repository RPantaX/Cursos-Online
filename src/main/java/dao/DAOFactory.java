package dao;

public abstract class DAOFactory {

	public static final int MYSQL=1;
	public static final int SQL=2;
	 
	public abstract UsuarioDAO getUsuarioDAO();
	public abstract ClienteDAO getClienteDAO();
	public abstract DocenteDAO getDocenteDAO();
	public abstract CursoDAO getCursoDAO();
	public abstract VentaDAO getVentaDAO();
	
	public static DAOFactory getDAOFactory(int qBD) {
		
		switch (qBD) {
		case MYSQL: {
			
			return new MySQLDAOFactory();
		}
		default:
			return null;
		}
	}
	
}
