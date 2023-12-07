package dao;

import java.util.List;

import model.VentaDTO;
import model.VentaDetalleDTO;

public interface VentaDAO {
	public int registrarVenta(VentaDTO ventaDTO, List<VentaDetalleDTO> ventaDetalleDTO);
}
