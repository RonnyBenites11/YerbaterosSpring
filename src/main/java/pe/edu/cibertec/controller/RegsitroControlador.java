package pe.edu.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import pe.edu.cibertec.dao.UsuarioService;


@Controller
public class RegsitroControlador {
	
	
	@GetMapping("/")
	public String iniciarSesion() {
		return "login";
	}
	
}
