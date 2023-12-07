package mantenimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteDAO; 
import model.TipoUsuarioDTO;
import model.UsuarioDTO;
import model.ClienteDTO;
import utils.MySQLConexion;

public class MySQLClienteDAO implements ClienteDAO {

	@Override
	public List<ClienteDTO> listar() {
		//Declarar las variables a usar
		List<ClienteDTO> lista = new ArrayList<ClienteDTO>();
		ResultSet rs= null; 
		Connection con = null;
		PreparedStatement pst=null;
		
		try {//establecer la conexion con la base de datos 
			con=MySQLConexion.getConexion();
			String sql= " select c.id, c.nombre, c.apePaterno, c.apeMaterno, c.email, c.celular,  u.username, u.contrasena, t.des_tipo_usuario "+
						" from tb_usuario u inner join  tb_cliente 		c  on u.id = c.id_usuario 	  "+
						" 					inner join 	tb_tipousuario 	t  on u.id_tipo_usuario=t.id  ";
			 
			pst=con.prepareStatement(sql);//tengo que pasarle la conexion
			 
			//Ejecutamos
			rs=pst.executeQuery();  
			
			while( rs.next()) {//recorrer 
				ClienteDTO clienteDTO = new ClienteDTO();
				clienteDTO.setCodigo(  rs.getInt(1));
				clienteDTO.setNombre(  rs.getString(2));
				clienteDTO.setApePaterno(rs.getString(3));
				clienteDTO.setApeMaterno(rs.getString(4));
				clienteDTO.setEmail(rs.getString(5));
				clienteDTO.setCelular(rs.getLong(6));
				
					UsuarioDTO usuarioDTO = new UsuarioDTO();
					usuarioDTO.setUsername(rs.getString(7));
					usuarioDTO.setContrasena(rs.getString(8));
					
							TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
							tipoUsuarioDTO.setDesTipoUsuario(rs.getString(9));
						
					usuarioDTO.setTipoUsuario(tipoUsuarioDTO);	
						
			   clienteDTO.setUsuario(usuarioDTO);
			   lista.add(clienteDTO);
			}

		} catch (Exception e) {
			 System.out.println("Error en la sentencia "+e.getMessage());
		} finally {//cerrar conexiones
			try {
				
				if (rs!=null)  rs.close();
				if (pst!=null) pst.close();
				if (con!=null) con.close();
				
				
			} catch (Exception e2) {
				 System.out.println("Error al cerrar conexiones "+e2.getMessage());
			}
		}
		
		return lista;
	}

	@Override
	public ClienteDTO obtenerCliente(int codigo) {
		//Declarar las variables a usar
		ClienteDTO  clienteDTO = new ClienteDTO();
		ResultSet rs= null; 
		Connection con = null;
		PreparedStatement pst=null;
		
		try {//establecer la conexion con la base de datos 
			con=MySQLConexion.getConexion();
			String sql= " select c.id, c.nombre, c.apePaterno, c.apeMaterno, c.email, c.celular,  u.username, u.contrasena, t.des_tipo_usuario "+
						" from tb_usuario u inner join  tb_cliente 		c  on u.id = c.id_usuario 	  "+
						" 					inner join 	tb_tipousuario 	t  on u.id_tipo_usuario=t.id  "+
						" where c.id=? ";
			pst=con.prepareStatement(sql);//tengo que pasarle la conexion
			
			//Parametrizar el PreparedStatement
			pst.setInt(1, codigo);
			
			//Ejecutamos
			rs=pst.executeQuery();  
			
			while( rs.next()) {//recorrer 
				clienteDTO = new ClienteDTO();
				clienteDTO.setCodigo(  rs.getInt(1));
				clienteDTO.setNombre(  rs.getString(2));
				clienteDTO.setApePaterno(rs.getString(3));
				clienteDTO.setApeMaterno(rs.getString(4));
				clienteDTO.setEmail(rs.getString(5));
				clienteDTO.setCelular(rs.getLong(6));
				
					UsuarioDTO usuarioDTO = new UsuarioDTO();
					usuarioDTO.setUsername(rs.getString(7));
					usuarioDTO.setContrasena(rs.getString(8));
					
							TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
							tipoUsuarioDTO.setDesTipoUsuario(rs.getString(9));
						
					usuarioDTO.setTipoUsuario(tipoUsuarioDTO);	
						
			   clienteDTO.setUsuario(usuarioDTO);
			 
			}

		} catch (Exception e) {
			 System.out.println("Error en la sentencia "+e.getMessage());
		} finally {//cerrar conexiones
			try {
				
				if (rs!=null)  rs.close();
				if (pst!=null) pst.close();
				if (con!=null) con.close();			
				
			} catch (Exception e2) {
				 System.out.println("Error al cerrar conexiones "+e2.getMessage());
			}
		}
		
		return clienteDTO;
	}

	@Override
	public int registrar(ClienteDTO clienteDTO) {
		int resultado=0;
		Connection con = null;
		PreparedStatement pst= null;
		
		try {
			  //Conexión
			  con = MySQLConexion.getConexion();
			  //Creado en query sql
			  String sql = " insert into tb_cliente values (null, ?,?,?,?,?, CURDATE(),1,null) ";
			  //Enviar la conexión y el sql al PreparedStatement
			  pst= con.prepareStatement(sql);
			  
			  //Parametrizar el PreparedStatement
			  pst.setString(1, clienteDTO.getNombre());
			  pst.setString(2, clienteDTO.getApePaterno());
			  pst.setString(3, clienteDTO.getApeMaterno());
			  pst.setString(4, clienteDTO.getEmail());
			  pst.setLong(5, clienteDTO.getCelular()); 
			  
			  //Ejecutar
			  resultado= pst.executeUpdate();
			 
		}catch (Exception e) {
			 System.out.println("Error en la sentencia "+e.getMessage());
		} finally {
			
				try {
					if (pst!=null)pst.close();
					if (con!=null)con.close();
				} catch (Exception e2) {
					 System.out.println("Error al cerrar "+e2.getMessage());
				}
		}
	
		return resultado;
	}

	@Override
	public int actualizar(ClienteDTO clienteDTO) {
		int resultado=0;
		Connection con = null;
		PreparedStatement pst= null;
		
		try {
			  //Conexión
			  con = MySQLConexion.getConexion();
			  //Creado en query sql
			  String sql = " update tb_cliente  set  nombre=?, apePaterno=?, apeMaterno=?, email=?, celular=?   where id=? "  ; 
			  //Enviar la conexión y el sql al PreparedStatement
			  pst= con.prepareStatement(sql);
			  
			  //Parametrizar el PreparedStatement
			  pst.setString(1, clienteDTO.getNombre());
			  pst.setString(2, clienteDTO.getApePaterno());
			  pst.setString(3, clienteDTO.getApeMaterno());
			  pst.setString(4, clienteDTO.getEmail());
			  pst.setLong(5, clienteDTO.getCelular()); 
			  pst.setInt(6,  clienteDTO.getCodigo());
			  
			  //Ejecutar
			  resultado= pst.executeUpdate();
			 
		}catch (Exception e) {
			 System.out.println("Error en la sentencia "+e.getMessage());
		} finally {
			
				try {
					if (pst!=null)pst.close();
					if (con!=null)con.close();
				} catch (Exception e2) {
					 System.out.println("Error al cerrar "+e2.getMessage());
				}
		}
	
		return resultado;
	}

	@Override
	public int eliminar(int codigo) {
		// declarar las variables a usar
		int resultado=0;
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con=MySQLConexion.getConexion();
			String sql="delete from tb_cliente where id=?" ;
			pst= con.prepareStatement(sql);
			
			pst.setInt(1, codigo);
			
			resultado=pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error en la sentencia "+e.getMessage());
		} finally {
			
			try {
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar "+e2.getMessage());
			}
		}		
		return  resultado;
	}

	@Override
	public ClienteDTO obtenerDatosClienteLogeado(String usuario, String contrasena) {
		
		//Creando los objetos
		ClienteDTO clienteDTO = null;
		Connection con = null;
		CallableStatement cst= null;
		ResultSet rs= null;
				
		try {
			con= MySQLConexion.getConexion();
			String sql =" call usp_obtenerDatosAcceso(?,?) ";
			cst=con.prepareCall(sql);
			
			//Parametrizar
			cst.setString(1, usuario);
		    cst.setString(2, contrasena);
			
		  //Ejecución
		   rs= cst.executeQuery();
		   while(rs.next()) {
				  
				clienteDTO = new ClienteDTO();
				clienteDTO.setCodigo(  rs.getInt(1));
				clienteDTO.setNombre(  rs.getString(2));
				clienteDTO.setApePaterno(rs.getString(3));
				clienteDTO.setApeMaterno(rs.getString(4));
				clienteDTO.setEmail(rs.getString(5));
				clienteDTO.setCelular(rs.getLong(6));
				
					UsuarioDTO usuarioDTO = new UsuarioDTO();
					usuarioDTO.setUsername(rs.getString(7));
					usuarioDTO.setContrasena(rs.getString(8));
					
							TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
							tipoUsuarioDTO.setDesTipoUsuario(rs.getString(9));
						
					usuarioDTO.setTipoUsuario(tipoUsuarioDTO);	
						
			   clienteDTO.setUsuario(usuarioDTO);
			 }

		} catch (Exception e) {
			System.out.println("Error en la sentencia "+e.getMessage());
		} finally {
			
			try {
				if (cst!=null) cst.close();
				if (rs!=null)  rs.close();
				if (con!=null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar "+e2.getMessage());
			}	
		}
		
		
		return clienteDTO;
	}

 
	
}
