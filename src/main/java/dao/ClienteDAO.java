package dao;

import java.util.List;
 
import model.ClienteDTO; 

public interface ClienteDAO {

	public List<ClienteDTO> listar();  
	public ClienteDTO obtenerCliente(int codigo); 
	public int registrar(ClienteDTO clienteDTO);
	public int actualizar(ClienteDTO clienteDTO);
	public int eliminar(int codigo); 
  
	public ClienteDTO obtenerDatosClienteLogeado(String usuario, String contrasena);
}

