package pe.edu.cibertec.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.entity.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {
	List<Terminal> findAllByDepartamento(int codigo);
}		
