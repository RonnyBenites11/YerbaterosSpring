package pe.edu.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.dao.DetalleVentaPasajeRepository;
import pe.edu.cibertec.entity.DetalleVentaPasaje;
import pe.edu.cibertec.entity.Viaje;

@Service
public class DetalleVentaPasajeService {
	@Autowired 
	private DetalleVentaPasajeRepository repoDetalle;
	
	public void grabar(DetalleVentaPasaje bean) {
		repoDetalle.save(bean);
	}
		
	
}
