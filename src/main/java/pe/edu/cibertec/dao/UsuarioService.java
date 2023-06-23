package pe.edu.cibertec.dao;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


import pe.edu.cibertec.entity.UsuarioRegistroDTO;
import pe.edu.cibertec.entity.UsuarioTA;


public interface UsuarioService  extends UserDetailsService{
	
public UsuarioTA guardar(UsuarioRegistroDTO registroDTO);
public List<UsuarioTA> listarUsuarios();
}
