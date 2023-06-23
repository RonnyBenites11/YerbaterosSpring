package pe.edu.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import java.util.List;
import pe.edu.cibertec.service.TerminalService;
import pe.edu.cibertec.service.DepartamentoService;
import pe.edu.cibertec.entity.Terminal;
import pe.edu.cibertec.entity.Departamento;

@Controller
@RequestMapping("/terminal")
public class TerminalController {
	@Autowired
	private DepartamentoService servicioDep;
	
	@Autowired
	private TerminalService servicioTer;
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		List<Departamento> info=servicioDep.listarTodos("nombre","asc");
		model.addAttribute("departamentos", info);
		
		List<Terminal> infoTer=servicioTer.listarTodos();
		model.addAttribute("terminales", infoTer);
		
		return "terminal";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Terminal buscar(@RequestParam("codigo") Integer cod) {
		return servicioTer.buscarPorID(cod);
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod, @RequestParam("nombres") String nom,
			@RequestParam("departamento") Integer codDep,
			RedirectAttributes redirect) {	
		try {
			Terminal ter = new Terminal();
			ter.setCodigo(cod);
			ter.setNombres(nom);
			
			Departamento departamento = new Departamento();
			departamento.setCodigo(codDep);
			ter.setDepartamento(departamento);
			
			servicioTer.grabar(ter);
			if(cod==0)
				redirect.addFlashAttribute("MENSAJE","Terminal registrado!");
			else
				redirect.addFlashAttribute("MENSAJE","Terminal actualizado!");
		}catch(Exception e) {
			redirect.addFlashAttribute("MENSAJE","ERROR! No se logro actualizar terminal");
			e.printStackTrace();
		}
		
		return "redirect:/terminal/lista";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") int cod ,RedirectAttributes redirect) {
		try {
			servicioTer.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE","Terminal eliminado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/terminal/lista";
	}
}
