package pe.edu.cibertec.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.edu.cibertec.entity.VentaPasaje;
import pe.edu.cibertec.service.VentaPasajeService;
import pe.edu.cibertec.utils.Libreria;

@Controller
@RequestMapping("/detalleventa")
public class ReporteVentaController {

	@Autowired
	private VentaPasajeService servicioVenta;
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		List<VentaPasaje> infoVen=servicioVenta.listarTodos();
		model.addAttribute("ventas", infoVen);
		return "detalleventa";
	}
	
	
	@RequestMapping("/reporte")
	public void reporte(HttpServletResponse response) {
		try {
			List<VentaPasaje> lista = servicioVenta.listarTodos();
			JRBeanCollectionDataSource origen = new JRBeanCollectionDataSource(lista);
			File archivo = ResourceUtils.getFile("classpath:ventas.jrxml");
			JasperPrint print = Libreria.generarReporte(archivo,origen);
			response.setContentType("application/pdf");
			OutputStream salida= response.getOutputStream();
			
			JasperExportManager.exportReportToPdfStream(print,salida);
			}catch(Exception e){
				e.printStackTrace();
			}
	}
}
