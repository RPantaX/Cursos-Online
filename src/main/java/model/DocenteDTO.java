package model;

public class DocenteDTO {
	
	private int codigo;
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String email;
	private long celular;
	private ProfesionDTO profesion;
	
	
	
	public DocenteDTO () {}

	public DocenteDTO(String nombre, String apePaterno, String apeMaterno, String email, long celular, ProfesionDTO profesion) {
		this.nombre = nombre;
		this.apePaterno = apePaterno;
		this.apeMaterno = apeMaterno;
		this.email = email;
		this.celular = celular;
		this.profesion = profesion;
	}

	public DocenteDTO(int codigo, String nombre, String apePaterno, String apeMaterno, String email, long celular, ProfesionDTO profesion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apePaterno = apePaterno;
		this.apeMaterno = apeMaterno;
		this.email = email;
		this.celular = celular;
		this.profesion = profesion;
	}

	
	public ProfesionDTO getProfesion() {
		return profesion;
	}

	public void setProfesion(ProfesionDTO profesion) {
		this.profesion = profesion;
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

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCelular() {
		return celular;
	}

	public void setCelular(long celular) {
		this.celular = celular;
	}

 

}
