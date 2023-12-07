package dao;

import java.util.List;

import model.DocenteDTO;

public interface DocenteDAO {
	public int registrar(DocenteDTO docente);
	public int actualizar(DocenteDTO docente);
	
	public List<DocenteDTO> listar();  
	public DocenteDTO obtenerDocente(int codigo); 
	public int eliminar(int codigo); 
}
