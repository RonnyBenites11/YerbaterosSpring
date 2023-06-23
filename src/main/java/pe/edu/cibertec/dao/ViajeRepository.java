package pe.edu.cibertec.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.entity.Viaje;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
	//List<Viaje> findAllByRuta(Ruta ruta);
	
	@Query("select v from Viaje v join fetch v.ruta r join fetch r.terminalOrigen to "
			+ "join fetch r.terminalDestino td join fetch to.departamento d1 join fetch td.departamento d2 "
			+ "where d1.codigo =?1 and d2.codigo =?2 " 
			+ "and cast(v.fechasPartida as string) = ?3 ")
	public List<Viaje> fetchByCodigosWithRutaTerminalDepartamento (Integer codDpto1, Integer codDpto2, String fecha);
	
	@Query("select v from Viaje v where v.ruta.codigo=?1")
	public List<Viaje> listaAllByRuta(int ruta);
}