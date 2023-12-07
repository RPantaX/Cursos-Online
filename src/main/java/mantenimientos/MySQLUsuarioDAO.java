package mantenimientos;

import java.sql.CallableStatement;
import java.sql.Connection; 
import java.sql.ResultSet; 

import dao.UsuarioDAO; 
import model.TipoUsuarioDTO;
import model.UsuarioDTO;
import utils.MySQLConexion;

public class MySQLUsuarioDAO implements UsuarioDAO {

	 

	@Override
	public UsuarioDTO validarUsuario(String usuario, String contrasena) {
		
		//Creando los objetos
		UsuarioDTO usuarioDTO = null;
		Connection con = null;
		CallableStatement cst= null;
		ResultSet rs= null;
				
		try {
			con= MySQLConexion.getConexion();
			String sql =" call usp_validaAcceso(?,?) ";
			cst=con.prepareCall(sql);
			
			//Parametrizar
			cst.setString(1, usuario);
		    cst.setString(2, contrasena);
			
		  //Ejecución
		   rs= cst.executeQuery();
		   while(rs.next()) {
				  
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setCodigo(  rs.getInt(1)); 
				usuarioDTO.setUsername(rs.getString(2));
				usuarioDTO.setContrasena(rs.getString(3));
					
				TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();  
				tipoUsuarioDTO.setCodTipoUsuario(rs.getInt(4));	       
				tipoUsuarioDTO.setDesTipoUsuario(rs.getString(5));	 
					
				usuarioDTO.setTipoUsuario(tipoUsuarioDTO); 
			 }

		} catch (Exception e) {
			System.out.println("Error en la sentencia "+e.getMessage());
		} finally {
			
			try {
				if (cst!=null) cst.close();
				if (rs!=null)  rs.close();
				if (con!=null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar conexiones "+e2.getMessage());
			}	
		}
		
		
		return usuarioDTO;
	}

 
	
	
}
