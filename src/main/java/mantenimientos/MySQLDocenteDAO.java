package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import dao.DocenteDAO;

import java.util.ArrayList;

import model.DocenteDTO;
import model.ProfesionDTO;
import utils.MySQLConexion;

public class MySQLDocenteDAO implements DocenteDAO{

	@Override
	public int registrar(DocenteDTO docente) {
		int resultado=0;
		Connection con = null;
		PreparedStatement pst= null;
		
		try {
			  //Conexión
			  con = MySQLConexion.getConexion();
			  //Creado en query sql
			  String sql = " insert into tb_docente values (null, ?,?,?,?,?,?,null) ";
			  //Enviar la conexión y el sql al PreparedStatement
			  pst= con.prepareStatement(sql);
			  
			  //Parametrizar el PreparedStatement
			  pst.setString(1, docente.getNombre());
			  pst.setString(2, docente.getApePaterno());
			  pst.setString(3, docente.getApeMaterno());
			  pst.setString(4, docente.getEmail());
			  pst.setLong(5, docente.getCelular());
			  pst.setInt(6, docente.getProfesion().getCodigo());
			  
			  //Ejecutar
			  resultado= pst.executeUpdate();
			 
		}catch (Exception e) {
			 System.out.println("Error en la sentencia "+e.getMessage());
		} finally {
			
				try {
					if (pst!=null)pst.close();
					if (con!=null)con.close();
				} catch (Exception e2) {
					 System.out.println("Error al cerrar conexiones "+e2.getMessage());
				}
		}
	
		return resultado;
	}

	@Override
	public int actualizar(DocenteDTO docente) {
		int resultado=0;
		Connection con = null;
		PreparedStatement pst= null;
		
		try {
			  //Conexión
			  con = MySQLConexion.getConexion();
			  //Creado en query sql
			  String sql = " update tb_docente set  nombre=?, apePaterno=?, apeMaterno=?, email=?, celular=?, id_profesion=? where id=? "  ; 
			  //Enviar la conexión y el sql al PreparedStatement
			  pst= con.prepareStatement(sql);
			  
			  //Parametrizar el PreparedStatement
			  pst.setString(1, docente.getNombre());
			  pst.setString(2, docente.getApePaterno());
			  pst.setString(3, docente.getApeMaterno());
			  pst.setString(4, docente.getEmail());
			  pst.setLong(5, docente.getCelular());
			  pst.setInt(6,  docente.getProfesion().getCodigo());
			  pst.setInt(7,  docente.getCodigo());
			  
			  //Ejecutar
			  resultado= pst.executeUpdate();
			 
		}catch (Exception e) {
			 System.out.println("Error en la sentencia "+e.getMessage());
		} finally {
			
				try {
					if (pst!=null)pst.close();
					if (con!=null)con.close();
				} catch (Exception e2) {
					 System.out.println("Error al cerrar conexiones "+e2.getMessage());
				}
		}
	
		return resultado;
	}

	@Override
	public List<DocenteDTO> listar() {

		//Declarar las variables a usar
		List<DocenteDTO> lista = new ArrayList<DocenteDTO>();
		ResultSet rs= null; 
		Connection con = null;
		PreparedStatement pst=null;
		
		try {//establecer la conexion con la base de datos 
			con=MySQLConexion.getConexion();
			String sql= " select d.id, d.nombre, d.apePaterno, d.apeMaterno, d.email, d.celular, d.id_profesion, p.des_profesion "+
						" from tb_docente d  inner join tb_profesion p on d.id_profesion=p.id ";
			pst=con.prepareStatement(sql);//tengo que pasarle la conexion
			
			//Ejecutamos
			rs=pst.executeQuery();  
			
			while( rs.next()) {//recorrer 
				DocenteDTO docente = new DocenteDTO();
				docente.setCodigo(  rs.getInt(1));
				docente.setNombre(  rs.getString(2));
				docente.setApePaterno(rs.getString(3));
				docente.setApeMaterno(rs.getString(4));
				docente.setEmail(rs.getString(5));
				docente.setCelular(rs.getLong(6));
				
				ProfesionDTO profesion = new ProfesionDTO(); //Creamos instancia de Profesion
				profesion.setCodigo(rs.getInt(7));	   //Llenamos el valor del codigo
				profesion.setDesProfesion(rs.getString(8));	//Llenamos  la descripción
				
				docente.setProfesion(profesion); //Agregamos el objeto profesion a docente profesion 	
				
				lista.add(docente);
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
	public DocenteDTO obtenerDocente(int codigo) {
		//Declarar las variables a usar
				DocenteDTO docente = null;
				ResultSet rs= null; 
				Connection con = null;
				PreparedStatement pst=null;
				
				try {//establecer la conexion con la base de datos 
					con=MySQLConexion.getConexion();
					String sql= " select d.id, d.nombre, d.apePaterno, d.apeMaterno, d.email, d.celular, d.id_profesion, p.des_profesion "+
							" from tb_docente d  inner join tb_profesion p on d.id_profesion=p.id  where d.id=?";
					pst=con.prepareStatement(sql);//tengo que pasarle la conexion
					//parametrizar
					pst.setInt(1, codigo);//aquí le paso el codigo
					
					//Ejecutamos
					rs=pst.executeQuery();  
					
					while( rs.next()) {//recorrer 
						docente = new DocenteDTO();
						docente.setCodigo(  rs.getInt(1));
						docente.setNombre(  rs.getString(2));
						docente.setApePaterno(rs.getString(3));
						docente.setApeMaterno(rs.getString(4));
						docente.setEmail(rs.getString(5));
						docente.setCelular(rs.getLong(6));
						
						ProfesionDTO profesion = new ProfesionDTO(); //Creamos instancia de Profesion
						profesion.setCodigo(rs.getInt(7));	   //Llenamos el valor del codigo		
						profesion.setDesProfesion(rs.getString(8));	//Llenamos  la descripción
						
						docente.setProfesion(profesion); //Agregamos el objeto profesion a docente profesion 	
						
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
				
				return docente;//devolución del objeto 
	}

	@Override
	public int eliminar(int codigo) {
		// declarar las variables a usar
		int resultado=0;
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con=MySQLConexion.getConexion();
			String sql="delete from tb_docente where id=?" ;
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

}