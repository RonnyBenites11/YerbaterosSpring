package pe.edu.cibertec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.dao.BusRepository;
import pe.edu.cibertec.entity.Bus;

@Service
public class BusService {
	@Autowired
	private BusRepository repo;
	
	public List<Bus> listarTodos(){
		return repo.findAll();
	}
	
	public List<Bus> listarPorMarca(String marca){
		return repo.findAllByMarca(marca);
	}
	
	public Bus buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public void grabar(Bus bean) {
		repo.save(bean);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
}
