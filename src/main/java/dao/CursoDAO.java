package dao;

import java.util.List;

import model.CursoDTO;

public interface CursoDAO {

	public int registrar(CursoDTO curso);
	public int actualizar(CursoDTO curso);
	
	public List<CursoDTO> listar();
	public CursoDTO obtenerCurso(int codigo);
	public int eliminar(int codigo);
	
}
