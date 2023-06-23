package pe.edu.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;



import pe.edu.cibertec.entity.VentaPasaje;
import pe.edu.cibertec.service.VentaPasajeService;
import pe.edu.cibertec.service.VentaPasajexViajeImpl;

@RestController
@RequestMapping("/api/v1")

public class ApiRest {

	
	@Autowired
	VentaPasajexViajeImpl ventaxviaje;
	VentaPasajeService ventservice;
	
	@GetMapping("/viajes")
	public List<VentaPasaje> listarVentaPasajes(){
		return ventaxviaje.obtenertodo();
	}
	@GetMapping("/viajes/{Id_Venta}")
	public ResponseEntity<VentaPasaje> obtnerVentaporID(@PathVariable int Id_Venta){
		VentaPasaje ventaporid = ventaxviaje.obtnerPorId(Id_Venta);
		return ResponseEntity.ok(ventaporid);
		}

	
}
