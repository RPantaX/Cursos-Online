package model;

public class VentaDetalleDTO {
	private int codDetVenta;
	private int codVenta;
	private int codCurso;
	private int cantidad;
	private double precio;
	public int getCodDetVenta() {
		return codDetVenta;
	}
	public void setCodDetVenta(int codDetVenta) {
		this.codDetVenta = codDetVenta;
	}
	public int getCodVenta() {
		return codVenta;
	}
	public void setCodVenta(int codVenta) {
		this.codVenta = codVenta;
	}
	public int getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
