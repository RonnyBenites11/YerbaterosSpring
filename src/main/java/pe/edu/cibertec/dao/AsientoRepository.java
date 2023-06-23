package pe.edu.cibertec.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.cibertec.entity.Asiento;

public interface AsientoRepository extends JpaRepository<Asiento, Integer> {
	@Query("select a from Asiento a join fetch a.viaje v join fetch v.ruta "
			+ "where a.viaje.codigo = ?1 ")
	public List<Asiento> listAllByViaje (Integer codViaje); 
}
