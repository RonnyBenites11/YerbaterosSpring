package pe.edu.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.dao.VentaxviajeRepository;
import pe.edu.cibertec.entity.VentaPasaje;
@Service
public class VentaPasajexViajeImpl implements VentaPasajexViaje {
 @Autowired
	VentaxviajeRepository ventaxviajerepo;
	@Override
	public List<VentaPasaje> obtenertodo() {
		return  ventaxviajerepo.findAll();
	}

	@Override
	public VentaPasaje obtnerPorId(int Id_Venta) {
		
		return  ventaxviajerepo.findById(Id_Venta).orElse(null);
	}

}
