package pe.edu.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.cibertec.entity.UsuarioRegistroDTO;

import pe.edu.cibertec.dao.UsuarioService;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
private  UsuarioService usuarioServicio;

public RegistroUsuarioController(UsuarioService usuarioServicio) {
	super();
	this.usuarioServicio = usuarioServicio;
}
@ModelAttribute ("usuario")
public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
	return new UsuarioRegistroDTO();
}
@GetMapping
public String mostrarfrmRegistro() {
	return "registro";
}
@PostMapping
public String registrarCuentaUsuario(@ModelAttribute ("usuario")UsuarioRegistroDTO registroDTO) {
	usuarioServicio.guardar(registroDTO);
	return "redirect:/registro?exito";
}


}
