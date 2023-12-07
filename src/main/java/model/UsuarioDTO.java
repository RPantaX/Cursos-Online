package model;

public class UsuarioDTO {

	private int codigo;
	private String username;
	private String contrasena;
	private TipoUsuarioDTO tipoUsuario;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(int codigo,  String username, String contrasena, TipoUsuarioDTO tipoUsuario) {
		this.codigo = codigo;
		this.username = username;
		this.contrasena = contrasena;
		this.tipoUsuario = tipoUsuario;
	}
	
	public UsuarioDTO( String username, String contrasena, TipoUsuarioDTO tipoUsuario) {
 
		this.username = username;
		this.contrasena = contrasena;
		this.tipoUsuario = tipoUsuario;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public TipoUsuarioDTO getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioDTO tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	 
	
}
