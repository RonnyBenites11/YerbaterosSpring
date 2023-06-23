package pe.edu.cibertec.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.entity.Bus;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Integer> {
	List<Bus> findAllByMarca(String marca);
}
