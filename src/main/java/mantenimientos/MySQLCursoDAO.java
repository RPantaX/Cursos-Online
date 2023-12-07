package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CursoDAO;
import model.CategoriaDTO;
import model.CursoDTO;
import model.ModalidadDTO;
import model.NivelDTO;
import utils.MySQLConexion;

public class MySQLCursoDAO implements CursoDAO {

	@Override
	public int registrar(CursoDTO curso) {
		//Declaración de variables
		int resultado=0;
		Connection con = null;
		PreparedStatement pst= null;
		
		try {
			  //Conexión
			  con = MySQLConexion.getConexion();
			  //Creado en query sql
			  String sql = " insert into tb_cursos values (null, ?,?,?,?,?,?) ";
			  //Enviar la conexión y el sql al PreparedStatement
			  pst= con.prepareStatement(sql);
			  
			  //Parametrizar el PreparedStatement
			  pst.setString(1, curso.getNombre());
			  pst.setInt(2, curso.getCategoria().getCodigo());
			  pst.setInt(3, curso.getNivel().getCodigo());
			  pst.setInt(4, curso.getModalidad().getCodigo());
			  pst.setInt(5, curso.getCreditos());
			  pst.setDouble(6, curso.getPrecio());
			  
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
	public int actualizar(CursoDTO curso) {
		//Declaración de variables
		int resultado=0;
		Connection con = null;
		PreparedStatement pst= null;
				
		try {
				//Conexión
				con = MySQLConexion.getConexion();
				//Creado en query sql
				String sql = " update  tb_cursos set  nombre=?, id_categoria=?, id_nivel=?, id_modalidad=?, creditos=?, precio=?  where id=? ";
				//Enviar la conexión y el sql al PreparedStatement
				pst= con.prepareStatement(sql);
					  
				//Parametrizar el PreparedStatement
				pst.setString(1, curso.getNombre());
				pst.setInt(2, curso.getCategoria().getCodigo());
				pst.setInt(3, curso.getNivel().getCodigo());
				pst.setInt(4, curso.getModalidad().getCodigo());
				pst.setInt(5, curso.getCreditos());
				pst.setDouble(6, curso.getPrecio());
				pst.setInt(7, curso.getCodigo());
					  
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
	public List<CursoDTO> listar() {
		//Paso 1 : declarar las variables a usar
		List<CursoDTO> lista = new ArrayList<CursoDTO>();
		ResultSet rs = null; 
		Connection con = null;
		PreparedStatement pst = null;
		try {
		   //Paso 2: obtener conexion a la base de datos 
		   con = MySQLConexion.getConexion();
		   //Paso 3: realizar la sentencia sql
		   String sql = " select a.id, a.nombre, 		"+
				   		"		 b.id as id_cat, b.des_categoria, 	"+
		   		 		"		 c.id as id_niv, c.des_nivel, 		"+
		   		 		" 		 d.id as id_mod, d.des_modalidad, 	"+
		   		 		" 		 a.creditos, a.precio 	"+
		   		 		" from tb_cursos a inner join tb_categoria b on a.id_categoria=b.id "+
		   		 		" 				   inner join tb_nivel     c on a.id_nivel=c.id 	"+
		   		 		" 				   inner join tb_modalidad d on a.id_modalidad=d.id "; 
		   //Paso 4: indicar la sentencia sql al objeto pstmt
		   pst = con.prepareStatement(sql);	 
		   //Paso 5: establecer los parametros ( si es que los hubiera depende de la sentencia sql ) 
		   //Paso 6: ejecutar sentencia sql
		   rs = pst.executeQuery(); 
		   //Paso 7: recorrer el bucle para obtener los registros que se obtuvieron en el paso 6
		   while (rs.next()){ //Extraer los datos 
			   CursoDTO curso = new CursoDTO(); 
			   curso.setCodigo(rs.getInt(1));
			   curso.setNombre(rs.getString(2));
			   
			   		CategoriaDTO categoria = new CategoriaDTO();
			   		categoria.setCodigo(rs.getInt(3));	
			   		categoria.setDesCategoria(rs.getString(4));
			   
			   curso.setCategoria(categoria);
			   
			   		NivelDTO nivel = new NivelDTO();
			   		nivel.setCodigo(rs.getInt(5));	
			   		nivel.setDesNivel(rs.getString(6));
			   
			   curso.setNivel(nivel);
			   
			   		ModalidadDTO modalidad= new ModalidadDTO();
			   		modalidad.setCodigo(rs.getInt(7));
			   		modalidad.setDesModalidad(rs.getString(8));
			   		
			   curso.setModalidad(modalidad);
			   
			   curso.setCreditos(rs.getInt(9));
			   curso.setPrecio(rs.getDouble(10));
			   
			   lista.add(curso);
		   }
		} catch (Exception e) {
		   System.out.println("Error en la sentencia " + e.getMessage());
		} finally {
			  try {//Paso 8 : cerrar los objetos 
				  if (rs!= null)    rs.close();
			      if (pst != null) 	pst.close();
			      if (con != null) 	con.close();
			   } catch (SQLException e) {
			      System.out.println("Error al cerrar conexión");
			   }
		}
		//Paso 9 : Se retorna el  objeto curso 
		return lista;
	}

	@Override
	public CursoDTO obtenerCurso(int codigo) {
		//Paso 1 : declarar las variables a usar
		CursoDTO curso = null; 
		ResultSet rs = null; 
		Connection con = null;
		PreparedStatement pst = null;
		try {
		   //Paso 2: obtener conexion a la base de datos 
		   con = MySQLConexion.getConexion();
		   //Paso 3: realizar la sentencia sql
		   String sql = " select a.id, a.nombre, 		"+
				   		"		 b.id as id_cat, b.des_categoria, 	"+
		   		 		"		 c.id as id_niv, c.des_nivel, 		"+
		   		 		" 		 d.id as id_mod, d.des_modalidad, 	"+
		   		 		" 		 a.creditos, a.precio 	"+
		   		 		" from tb_cursos a inner join tb_categoria b on a.id_categoria=b.id "+
		   		 		" 				   inner join tb_nivel     c on a.id_nivel=c.id 	"+
		   		 		" 				   inner join tb_modalidad d on a.id_modalidad=d.id "+
		   		 		" where a.id=? 	";
		   //Paso 4: indicar la sentencia sql al objeto pstmt
		   pst = con.prepareStatement(sql);	 
		   //Paso 5: establecer los parametros ( si es que los hubiera depende de la sentencia sql ) 
		   pst.setInt(1,codigo);
		   //Paso 6: ejecutar sentencia sql
		   rs = pst.executeQuery(); 
		   //Paso 7: recorrer el bucle para obtener los registros que se obtuvieron en el paso 6
		   while (rs.next()){ //Extraer los datos 
			   curso = new CursoDTO(); 
			   curso.setCodigo(rs.getInt(1));
			   curso.setNombre(rs.getString(2));
			   
			   		CategoriaDTO categoria = new CategoriaDTO();
			   		categoria.setCodigo(rs.getInt(3));	
			   		categoria.setDesCategoria(rs.getString(4));
			   
			   curso.setCategoria(categoria);
			   
			   		NivelDTO nivel = new NivelDTO();
			   		nivel.setCodigo(rs.getInt(5));	
			   		nivel.setDesNivel(rs.getString(6));
			   
			   curso.setNivel(nivel);
			   
			   		ModalidadDTO modalidad= new ModalidadDTO();
			   		modalidad.setCodigo(rs.getInt(7));
			   		modalidad.setDesModalidad(rs.getString(8));
			   		
			   curso.setModalidad(modalidad);
			   
			   curso.setCreditos(rs.getInt(9));
			   curso.setPrecio(rs.getDouble(10));  
		   }
		} catch (Exception e) {
		   System.out.println("Error en la sentencia " + e.getMessage());
		} finally {
			  try {//Paso 8 : cerrar los objetos 
				  if (rs!= null)    rs.close();
			      if (pst != null) 	pst.close();
			      if (con != null) 	con.close();
			   } catch (SQLException e) {
			      System.out.println("Error al cerrar conexión");
			   }
		}
		//Paso 9 : Se retorna el  objeto curso 
		return curso;
	}

	@Override
	public int eliminar(int codigo) {
		//Paso 1 : declarar las variables a usar
		int resultado=0;
		Connection con= null;
		PreparedStatement pst=null;
		try {
			//Paso 2: obtener conexion a la base de datos 
			con=MySQLConexion.getConexion();
			//Paso 3: realizar la sentencia sql
			String sql=" delete from  tb_cursos   where id=?  ";
			//Paso 4: indicar la sentencia sql al objeto pst
			pst=con.prepareStatement(sql);
			//Paso 5: Parametrizar en el orden de los signos de ?  inicia en 1 
			//        ( si es que los hubiera depende de la sentencia sql ) 
			pst.setInt(1,codigo);
			//Paso 6: ejecutar sentencia sql
			resultado=pst.executeUpdate();	
		} catch (Exception e) {
			System.out.println("Error en la sentencia "+e.getMessage());
		}finally{
			try { //Paso 7 : cerrar los objetos 
				if(pst!=null)pst.close();
				if(con!=null)con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar "+e2.getMessage());
			}	
		}
		//Paso 8 : Se retorna la cantidad de filas afectadas
		return resultado;
	}

 

}
