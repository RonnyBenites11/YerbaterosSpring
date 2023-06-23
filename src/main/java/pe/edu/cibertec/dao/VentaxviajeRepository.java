package pe.edu.cibertec.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.entity.VentaPasaje;

@Repository
public interface VentaxviajeRepository extends JpaRepository<VentaPasaje, Integer> {

}
