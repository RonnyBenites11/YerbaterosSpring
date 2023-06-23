package pe.edu.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.cibertec.entity.VentaPasaje;
import pe.edu.cibertec.entity.Viaje;
import pe.edu.cibertec.entity.DetalleVentaPasaje;
import pe.edu.cibertec.entity.DetalleVentaPasajePK;
import pe.edu.cibertec.dao.VentaPasajeRepository;
import pe.edu.cibertec.dao.DetalleVentaPasajeRepository;

@Service
public class VentaPasajeService {
	@Autowired
	private VentaPasajeRepository repoVenta;
	
	@Autowired 
	private DetalleVentaPasajeRepository repoDetalle;
	
	@Transactional
	public void grabarBoleta(VentaPasaje ven) {
		try {
			repoVenta.save(ven);
			for(DetalleVentaPasaje dvp: ven.getListaDetalleVentaPasaje()) {
				DetalleVentaPasajePK pk = new DetalleVentaPasajePK();
				pk.setIdVenta(ven.getIdVenta());
				pk.setCodigoAsiento(dvp.getAsiento().getCodigo());
				//grabar detalle
				repoDetalle.save(dvp);
			}
			 
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void grabar(VentaPasaje bean) {
		repoVenta.save(bean);
	}
		
	public List<VentaPasaje> ListarVentaPasajePorUsuario(String sortField, String sortDirection, Integer codUsuario) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		
		return repoVenta.listVentaPasajeByUser(codUsuario, sort);				
	}
	
	public List<VentaPasaje> listarTodos(){
		return repoVenta.listarVentaPasaje();
	}
}
