package pe.edu.cibertec.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.UsuarioRegistroDTO;
import pe.edu.cibertec.entity.UsuarioTA;
import pe.edu.cibertec.dao.UsuarioRepository;
import pe.edu.cibertec.dao.UsuarioService;
import pe.edu.cibertec.entity.Rol;


@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuriorepositorio;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;



	


	@Override
	public UsuarioTA guardar(UsuarioRegistroDTO registroDTO) {
		UsuarioTA usuario = new UsuarioTA(registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),Arrays.asList(new Rol("ROLE_USER")));
		return usuriorepositorio.save(usuario);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioTA usuario = usuriorepositorio.findByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o Contraseña Invalidos");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
				
	}
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	@Override
	public List<UsuarioTA> listarUsuarios() {
		return usuriorepositorio.findAll();
	}

}
