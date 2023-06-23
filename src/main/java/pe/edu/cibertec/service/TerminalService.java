package pe.edu.cibertec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.dao.TerminalRepository;
import pe.edu.cibertec.entity.Terminal;

@Service
public class TerminalService {
	@Autowired
	private TerminalRepository repo;
	
	public List<Terminal> listarTodos(){
		return repo.findAll();
	}
	
	public Terminal buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public void grabar(Terminal bean) {
		repo.save(bean);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
	
	public List<Terminal> listarPorDepartamento(Integer codigo){
		return repo.findAllByDepartamento(codigo);
	}	
	
}
