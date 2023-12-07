package model;

public class CursoDTO {

	
	private int codigo;
	private String nombre;
	private CategoriaDTO categoria;
	private NivelDTO nivel;
	private ModalidadDTO modalidad;
	private int creditos;
	private double precio;
	
	public CursoDTO() {};
 
	

	public CursoDTO(String nombre, CategoriaDTO categoria, NivelDTO nivel, ModalidadDTO modalidad, int creditos,double precio) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.nivel = nivel;
		this.modalidad = modalidad;
		this.creditos = creditos;
		this.precio = precio;
	}
 

	public CursoDTO(int codigo, String nombre, CategoriaDTO categoria, NivelDTO nivel, ModalidadDTO modalidad,int creditos, double precio) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.categoria = categoria;
		this.nivel = nivel;
		this.modalidad = modalidad;
		this.creditos = creditos;
		this.precio = precio;
	}


	

	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public CategoriaDTO getCategoria() {
		return categoria;
	}



	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}



	public NivelDTO getNivel() {
		return nivel;
	}



	public void setNivel(NivelDTO nivel) {
		this.nivel = nivel;
	}



	public ModalidadDTO getModalidad() {
		return modalidad;
	}



	public void setModalidad(ModalidadDTO modalidad) {
		this.modalidad = modalidad;
	}



	public int getCreditos() {
		return creditos;
	}



	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}

 
	
	
}

