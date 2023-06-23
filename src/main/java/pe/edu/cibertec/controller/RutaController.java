package pe.edu.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import pe.edu.cibertec.service.RutaService;
import pe.edu.cibertec.service.TerminalService;
import pe.edu.cibertec.entity.Ruta;
import pe.edu.cibertec.entity.Terminal;

@Controller
@RequestMapping("/ruta")
public class RutaController {
	@Autowired
	private TerminalService servicioTer1;
	
	@Autowired
	private TerminalService servicioTer2;
	
	@Autowired
	private RutaService servicioRut;
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		List<Terminal> info1=servicioTer1.listarTodos();
		model.addAttribute("terminales1", info1);
		
		List<Terminal> info2=servicioTer2.listarTodos();
		model.addAttribute("terminales2", info2);
		
		List<Ruta> infoRut=servicioRut.listarTodos();
		model.addAttribute("rutas", infoRut);
		
		return "ruta";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Ruta buscar(@RequestParam("codigo") Integer cod) {
		return servicioRut.buscarPorID(cod);
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod, @RequestParam("alias") String ali,
			@RequestParam("tiempo") int tpo, @RequestParam("distancia") int dist, 
			@RequestParam("terOrigen") Integer tOrigen, @RequestParam("terDestino") Integer tDestino,
			RedirectAttributes redirect) {	
		try {
			Ruta rut = new Ruta();
			rut.setCodigo(cod);
			rut.setAlias(ali);
			rut.setTiempoRuta(tpo);
			rut.setDistancia(dist);
			
			Terminal terminal1 = new Terminal();
			Terminal terminal2 = new Terminal();
			terminal1.setCodigo(tOrigen);
			terminal2.setCodigo(tDestino);
			rut.setTerminalOrigen(terminal1);
			rut.setTerminalDestino(terminal2);
						
			servicioRut.grabar(rut);
			if(cod==0)
				redirect.addFlashAttribute("MENSAJE","Ruta registrado!");
			else
				redirect.addFlashAttribute("MENSAJE","Ruta actualizado!");
		}catch(Exception e) {
			redirect.addFlashAttribute("MENSAJE","ERROR! No se logro actualizar Ruta");
			e.printStackTrace();
		}
		
		return "redirect:/ruta/lista";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") int cod ,RedirectAttributes redirect) {
		try {
			servicioRut.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE","Ruta eliminada!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/ruta/lista";
	}
}
