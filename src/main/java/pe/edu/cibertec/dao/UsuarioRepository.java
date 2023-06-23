package pe.edu.cibertec.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import pe.edu.cibertec.entity.UsuarioTA;
@Repository
public interface UsuarioRepository  extends JpaRepository<UsuarioTA, Integer>{
	public UsuarioTA findByEmail(String email);

}
