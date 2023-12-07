package dao;

import mantenimientos.MySQLClienteDAO;
import mantenimientos.MySQLCursoDAO;
import mantenimientos.MySQLDocenteDAO;
import mantenimientos.MySQLUsuarioDAO;
import mantenimientos.MySQLVentaDAO; 

public class MySQLDAOFactory extends DAOFactory {

 
	@Override
	public DocenteDAO getDocenteDAO() {
	 
		return new MySQLDocenteDAO();
	}

	@Override
	public CursoDAO getCursoDAO() {
		 
		return new MySQLCursoDAO();
	}

	@Override
	public ClienteDAO getClienteDAO() {
		return new MySQLClienteDAO();
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new MySQLUsuarioDAO();
	}

	@Override
	public VentaDAO getVentaDAO() {
		// TODO Auto-generated method stub
		return new MySQLVentaDAO();
	}

}
