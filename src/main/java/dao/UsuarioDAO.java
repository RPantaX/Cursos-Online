package dao;
 
import model.UsuarioDTO;

public interface UsuarioDAO {
 
	public UsuarioDTO validarUsuario(String usuario, String contrasena);
}

