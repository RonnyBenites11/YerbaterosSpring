package pe.edu.cibertec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import pe.edu.cibertec.dao.DepartamentoRepository;
import pe.edu.cibertec.entity.Departamento;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoRepository repo;
	
	public List<Departamento> listarTodos(String sortField, String sortDirection){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		
		return repo.findAll(sort);
	}
	
}
