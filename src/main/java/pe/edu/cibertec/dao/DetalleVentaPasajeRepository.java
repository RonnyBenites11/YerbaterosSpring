package pe.edu.cibertec.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import pe.edu.cibertec.entity.DetalleVentaPasaje;
import pe.edu.cibertec.entity.Viaje;

public interface DetalleVentaPasajeRepository extends JpaRepository<DetalleVentaPasaje, Integer>{

	
}
