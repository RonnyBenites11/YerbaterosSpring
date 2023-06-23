package pe.edu.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import java.util.List;
import pe.edu.cibertec.service.BusService;
import pe.edu.cibertec.entity.Bus;

@Controller
@RequestMapping("/bus")
public class BusController {
	@Autowired
	private BusService servicioBu;
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		List<Bus> infoBu=servicioBu.listarTodos();
		model.addAttribute("buses", infoBu);
		
		return "bus";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Bus buscar(@RequestParam("codigo") Integer cod) {
		return servicioBu.buscarPorID(cod);
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod,
			@RequestParam("registro") String reg, @RequestParam("cantidad") int ctd,
			@RequestParam("marca") String mar, @RequestParam("modelo") String mod,
			RedirectAttributes redirect) {	
		try {
			Bus bu = new Bus();
			bu.setCodigo(cod);
			bu.setRegistro(reg);
			bu.setCtdAsientos(ctd);
			bu.setMarca(mar);
			bu.setModelo(mod);
			servicioBu.grabar(bu);
			if(cod==0)
				redirect.addFlashAttribute("MENSAJE","Bus registrado!");
			else
				redirect.addFlashAttribute("MENSAJE","Bus actualizado!");
		}catch(Exception e) {
			redirect.addFlashAttribute("MENSAJE","ERROR! No se logro actualizar bus");
			e.printStackTrace();
		}
		
		return "redirect:/bus/lista";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") int cod ,RedirectAttributes redirect) {
		try {
			servicioBu.eliminar(cod);
			redirect.addFlashAttribute("MENSAJE","Bus eliminado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/bus/lista";
	}
}
