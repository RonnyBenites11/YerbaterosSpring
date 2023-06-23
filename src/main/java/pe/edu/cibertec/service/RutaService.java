package pe.edu.cibertec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.dao.RutaRepository;
import pe.edu.cibertec.entity.Ruta;

@Service
public class RutaService {
	@Autowired
	private RutaRepository repo;
	
	public List<Ruta> listarTodos(){
		return repo.findAll();
	}
	
	public Ruta buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public void grabar(Ruta bean) {
		repo.save(bean);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
}
