package pe.edu.cibertec.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Sort;

import pe.edu.cibertec.entity.DetalleVentaPasaje;
import pe.edu.cibertec.entity.VentaPasaje;

public interface VentaPasajeRepository extends JpaRepository<VentaPasaje, Integer>{
	@Query("select v from VentaPasaje v "
			+ "where v.usuario.codigo = ?1")
	public List<VentaPasaje> listVentaPasajeByUser (Integer codUsuario, Sort sort); 
	
	@Query("select v from VentaPasaje v")
	public List<VentaPasaje> listarVentaPasaje();
}
