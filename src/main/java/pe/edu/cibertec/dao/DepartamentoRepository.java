package pe.edu.cibertec.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
}	
