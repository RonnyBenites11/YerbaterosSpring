package pe.edu.cibertec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.dao.AsientoRepository;
import pe.edu.cibertec.entity.Asiento;

@Service
public class AsientoService {
	@Autowired
	private AsientoRepository repo;
	
	public List<Asiento> listarTodos(){
		return repo.findAll();
	}
	
	public List<Asiento> listarPorViaje(Integer codViaje) {
		return repo.listAllByViaje(codViaje);
	}
	
	public Asiento buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public void grabar(Asiento bean) {
		repo.save(bean);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
}
