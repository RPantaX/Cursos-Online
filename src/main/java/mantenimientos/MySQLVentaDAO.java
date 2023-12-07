package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.VentaDAO;
import model.VentaDTO;
import model.VentaDetalleDTO;
import utils.MySQLConexion;

public class MySQLVentaDAO implements VentaDAO {

	@Override
	public int registrarVenta(VentaDTO ventaDTO, List<VentaDetalleDTO> ventaDetalleDTO) {

		int resultado=0;
		Connection con= null;
		ResultSet rs=null;
		PreparedStatement pst1=null;
		PreparedStatement pst2=null;
		int idCabVenta=0;
		
		try {
			con=MySQLConexion.getConexion();
			//cuando manejamos una sola tabla, tiene el autocomit por default, en cambio cuando modificamos 2 tablas tenemos que manejar nosotros el commit y rollbak
			con.setAutoCommit(false);
			String sqli="insert into tb_venta values (null, ?, curdate(), ?)";
			pst1=con.prepareStatement(sqli);
			
			//parametrizar en el orden de los signos de ? inicia en 1
			pst1.setInt(1, ventaDTO.getCodCliente());
			pst1.setDouble(2, ventaDTO.getTotal());
			
			//EJECUCION
			resultado=pst1.executeUpdate();
			//-----------------------------------------
			
			//Obtener el id de la cabecera
			String sql2="select @@identity as idCabVenta";
			rs=pst1.executeQuery(sql2);
			
			rs.next();
			idCabVenta=rs.getInt("idCabVenta");
			rs.close();
			
			//-----------------------------------------------------------------
			
			String sql3="insert into tb_venta_detalle values (null, ?, ?, ?, ?)";
			
			for (VentaDetalleDTO venDet : ventaDetalleDTO) {
				
				pst2=con.prepareStatement(sql3);
				pst2.setInt(1, idCabVenta);
				pst2.setInt(2, venDet.getCodCurso());
				pst2.setInt(3, venDet.getCantidad());
				pst2.setDouble(4, venDet.getPrecio());
				resultado=pst2.executeUpdate();
			}
			//cuando todo esté ok, recién hacemos el commit, y si hay error se maneja el catch y hace rollback para 
			//evitar errores y ninguna tabla guarde datos.
			con.commit();
		} catch(Exception e) {
			System.out.println("Error en la sentencia "+e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if(pst1!=null)pst1.close();
				if(pst2!=null)pst2.close();
				if(rs!=null)rs.close();
				if(con!=null)con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.out.println("Error al cerrar conexiones "+e2.getMessage());
			}
		}
		return 0;
	}

}
