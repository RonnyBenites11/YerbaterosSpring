package pe.edu.cibertec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.dao.ViajeRepository;
import pe.edu.cibertec.entity.Viaje;

@Service
public class ViajeService {
	@Autowired
	private ViajeRepository repo;
	
	public List<Viaje> listarTodos(){
		return repo.findAll();
	}
	
	public Viaje buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public List<Viaje> buscarPorDepartamentos(Integer codDpto1, Integer codDpto2, String fecha) {
		return repo.fetchByCodigosWithRutaTerminalDepartamento(codDpto1, codDpto2, fecha);
	}
	
	public void grabar(Viaje bean) {
		repo.save(bean);
	}
	
	public void eliminar(int cod) {
		repo.deleteById(cod);
	}
	
	public List<Viaje> listarViajesPorRuta(int codRuta){
		return repo.listaAllByRuta(codRuta);
	}
}
