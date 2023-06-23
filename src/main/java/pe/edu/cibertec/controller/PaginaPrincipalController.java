package pe.edu.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.ui.Model;
import pe.edu.cibertec.service.ViajeService;
import pe.edu.cibertec.service.AsientoService;
import pe.edu.cibertec.service.DepartamentoService;
import pe.edu.cibertec.service.VentaPasajeService;
import pe.edu.cibertec.service.DetalleVentaPasajeService;
import pe.edu.cibertec.entity.VentaPasaje;
import pe.edu.cibertec.entity.DetalleVentaPasaje;
import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.entity.Viaje;
import pe.edu.cibertec.entity.Detalle;
import pe.edu.cibertec.entity.Asiento;
import pe.edu.cibertec.entity.Departamento;

import pe.edu.cibertec.entity.Cliente;

@Controller
@RequestMapping("/pagina-principal")
public class PaginaPrincipalController {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private AsientoService servicioAsi;
	
	@Autowired
	private DepartamentoService servicioDep1;
	
	@Autowired
	private DepartamentoService servicioDep2;
	
	@Autowired
	private ViajeService servicioVia;
	
	@Autowired
	private VentaPasajeService servicioVen;

	@Autowired
	private DetalleVentaPasajeService servicioDet;
	
	@RequestMapping("/")
	public String lista(Model model) {
		List<Departamento> info1=servicioDep1.listarTodos("nombre","asc");
		model.addAttribute("departamentos1", info1);
		
		List<Departamento> info2=servicioDep2.listarTodos("nombre","asc");
		model.addAttribute("departamentos2", info2);
		
		//List<Bus> infoB=servicioBu.listarPorMarca("Toyota");
		//model.addAttribute("departamentos2", infoB);
		
		return "pagina-principal";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		List<Viaje> infoV=servicioVia.listarTodos();
		model.addAttribute("viajes", infoV);
		
		return "dashboard";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Model model, @RequestParam("origen") Integer codOrigen, @RequestParam("destino") Integer codDestino,
		@RequestParam("fecha") String fechaViaje,
		RedirectAttributes redirect) throws ParseException {	
		List<Departamento> info1=servicioDep1.listarTodos("nombre","asc");
		model.addAttribute("departamentos1", info1);
		
		List<Departamento> info2=servicioDep2.listarTodos("nombre","asc");
		model.addAttribute("departamentos2", info2);
		
		
		model.addAttribute("codOrigen", codOrigen);
		model.addAttribute("codDestino", codDestino);
		model.addAttribute("fechaViaje", fechaViaje);
		
		List<Viaje> infoV=servicioVia.buscarPorDepartamentos(codOrigen, codDestino, fechaViaje);
		model.addAttribute("viajes", infoV);
		
		return "busqueda";
	}
	
	@RequestMapping("/busqueda")
	public String busqueda(Model model, @RequestParam("origen") Integer codOrigen, @RequestParam("destino") Integer codDestino,
			@RequestParam("fecha") String fechaViaje,
			RedirectAttributes redirect) {
		List<Departamento> info1=servicioDep1.listarTodos("nombre","asc");
		model.addAttribute("departamentos1", info1);
		
		List<Departamento> info2=servicioDep2.listarTodos("nombre","asc");
		model.addAttribute("departamentos2", info2);
		
		
		model.addAttribute("codOrigen", codOrigen);
		model.addAttribute("codDestino", codDestino);
		model.addAttribute("fechaViaje", fechaViaje);
		
		List<Viaje> infoV=servicioVia.buscarPorDepartamentos(codOrigen, codDestino, fechaViaje);
		model.addAttribute("viajes", infoV);
				
		return "busqueda";
	}
	
	@RequestMapping("/ventaPasaje")
	public String ventaPasaje(Model model, @RequestParam("origen") String origen ,@RequestParam("destino") String destino ,@RequestParam("fecha") String fecha ,			
			@RequestParam("codBus") int codBus ,@RequestParam("codViaje") int codViaje ,RedirectAttributes redirect) {		
		List<Asiento> infoA=servicioAsi.listarPorViaje(codViaje);
		model.addAttribute("asientos", infoA);
		
		model.addAttribute("origen", origen);
		model.addAttribute("destino", destino);
		model.addAttribute("fecha", fecha);
		
		model.addAttribute("codViaje", codViaje);
		model.addAttribute("codBus", codBus);
		
		return "venta-pasaje";
	}
	
	@RequestMapping("/adicionar")
	@ResponseBody
	public List<Detalle> adicionar(@RequestParam("codigo") Integer cod, @RequestParam("asiento") String asi,
							@RequestParam("precio") double pre, HttpSession session) {
		List<Detalle> lista=null;
		if(session.getAttribute("carrito")==null)
			lista=new ArrayList<Detalle>();
		else
			lista=(List<Detalle>) session.getAttribute("carrito");
		
		Detalle det=new Detalle();
		det.setCodigo(cod);
		det.setAsiento(asi);
		det.setPrecio(pre);
		lista.add(det);
		session.setAttribute("carrito", lista);
		return lista;
	}
	
	@RequestMapping("/eliminar")
	@ResponseBody
	public List<Detalle> eliminar(@RequestParam("asiento") String cod,HttpSession session) {
		List<Detalle> lista=(List<Detalle>) session.getAttribute("carrito");
		for(Detalle det:lista) {
			if(det.getAsiento().equals(cod)) {
				lista.remove(det);
				break;
			}
		}
		
		session.setAttribute("carrito", lista);
		return lista;
	}
	
	@RequestMapping("/grabar")
	@ResponseBody
	public String grabar(
						@RequestParam("fecha") String fec, @RequestParam("viaje") int codViaje,
						@RequestParam("total") double tot, HttpSession session, RedirectAttributes redirec) {
		try {
			VentaPasaje bean = new VentaPasaje();
			bean.setUsuario(new Usuario(3));
			
			LocalDateTime datetime = LocalDateTime.now();
			bean.setFechaVenta(datetime);
			
			//bean.setFechaVenta(Date.valueOf(fec));
			bean.setMonto(tot);
			bean.setViaje(new Viaje(codViaje));
			servicioVen.grabar(bean);
			
			List<VentaPasaje> infoVen=servicioVen.ListarVentaPasajePorUsuario("fechaVenta","desc",3);
			Integer codVenta = 0;
			for(int indice=0; indice<1; indice++) {
				codVenta = infoVen.get(indice).getIdVenta();
			}
			
			List<Detalle> lista=(List<Detalle>) session.getAttribute("carrito");
			List<DetalleVentaPasaje> data=new ArrayList<DetalleVentaPasaje>();
			for(Detalle d: lista) {
				DetalleVentaPasaje mhb = new DetalleVentaPasaje(); 
				
				mhb.setVentapasaje(new VentaPasaje(codVenta));
				mhb.setAsiento(new Asiento(d.getCodigo()));
				mhb.setCliente(new Cliente(2));
				mhb.setPrecio(d.getPrecio());
				mhb.setDescuento(0);
				mhb.setSubtotal(d.getPrecio()-0);
				//data.add(mhb);
				
				
				Asiento asiento = servicioAsi.buscarPorID(d.getCodigo()); 
				asiento.setEstado(2);
				
				
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);
				
				String correo="i201918334@cibertec.edu.pe";
				
				
				 String body = "<style> * {font-family: 'Courier New', Courier, monospace;}.container{display: flex; justify-content: center; }";
	             body += ".text-center{ text-align: center; }.boleta-container{ width: 600px; }";
	             body += ".header-boleta{ color: white; background-color: forestgreen; padding: 15px; display: flex; flex-direction: column; align-items: center; }";
	             body += ".text-header{ margin-bottom: 30px; font-weight: 900; }";
	             body += ".partlow-header{ display: flex; width: 100 %; border-top: 3px solid white; justify-content: space-between; padding-top: 15px; }";
	             body += ".detail-container{ margin-top: 50px; }.detail-tittle-container{ padding: 10px; background-color: rgb(209, 224, 235); border: 1px solid gainsboro; border-radius: 5px 5px 0px 0px; }";
	             body += ".detail-tittle{font-weight: 800; }.detail-data-container{ display: flex; flex-direction: column; border: 1px solid gainsboro; padding: 15px; }.row{ display: flex; justify-content: space-between; padding: 10px 0px; }";
	             body += ".colum{ display: flex; flex-direction: column; }.icono{ width: 20px;}";
	             body += "</style><h2 class='text-center'>Detalle de su Compra</h2><br/>";
	             body += "<div class='container'><div class='boleta-container'><div class='header-boleta'><div class='text-header'>Confirmacion del Pasaje</div>";
	             body += "<span class='text-header'>Reserva confirmada</span><div class='partlow-header'><span>Codigo del pasaje: "+codVenta+"</span> <span>|</span><span>Datos sin mas</span></div></div>";
	             body += "<div class='detail-container'><div class='detail-tittle-container'><span class='detail-tittle'>Detalles del Pasaje</span></div>";
	             body += "<div class='detail-data-container'><div class='row' style='border-bottom: 4px dashed gainsboro;'><div class='colum'><span>Fecha del viaje</span> <div class='row'><img src = 'https://cdn-icons-png.flaticon.com/512/2693/2693507.png' class='icono'><span>"+"16/11/2022"+"</span></div><div class='colum'></div></div></div>";
	             body += "<div class='row' style='border-bottom: 4px dashed gainsboro;'><div class='colum'><span>Viajes</span> <div class='row'><img src = 'https://cdn-icons-png.flaticon.com/512/2/2322.png' class='icono'><span>"+"Lima"+"</span></div><div class='colum'></div></div>";
	             body += "<div class='colum'><span>Monto de Pago</span> <div class='row'><img src = 'https://cdn-icons-png.flaticon.com/512/126/126230.png' class='icono'><span>" +mhb.getSubtotal()+ "</span></div></div></div>";
	             body += "<div class='row' style='border-bottom: 4px dashed gainsboro;'><div class='colum'><span>Punto de Embarque</span> <div class='row'><img src = 'https://cdn-icons-png.flaticon.com/512/535/535188.png' class='icono'><span>"+"Lima"+"</span></div><div class='colum'></div></div>";
	             body += "<div class='colum'><span>Punto de Llegada</span> <div class='row'><img src = 'https://cdn-icons-png.flaticon.com/512/535/535188.png' class='icono'><span>"+"Huanuco"+"</span></div></div></div>";
	             body += "<div class='row' style='border-bottom: 4px dashed gainsboro;'><div class='colum'><span>Detalles del Viajero</span> <div class='row'><img src='https://cdn.icon-icons.com/icons2/1369/PNG/512/-person_90382.png' class='icono'><span>"+"Ronny"+"</span></div><div class='colum'></div></div>";
	             body += "<div class='colum' ><div class='row' style='padding: 0px;'><span>Asiento Nro</span> <span>Cod Cliente</span></div><div class='row' style='justify-content: space-around;'><div class='colum'>" +"43"+ "</div><div class='colum'>" +"2"+ "</div></div></div></div>";
	             body += "<div class='row'><div class='colum'><span>Email:</span> <div class='row'><span>" + correo + "</span></div></div><div class='colum'><span>Movil:</span> <div class='row'><span>94949</span></div></div></div></div> </div></div></div>";

				
				
				
	             helper.setFrom("yerbaterossa@gmail.com","Yerbateros SAC");
	             helper.setTo(correo);
	             helper.setSubject("Detalles de su compra");
	             helper.setText(body,true);
				
				mailSender.send(message);
				
				
				
				
				
				servicioDet.grabar(mhb);
				servicioAsi.grabar(asiento);
			}
			lista.clear();
			
			session.setAttribute("carrito",lista);
			//redirec.addFlashAttribute("MENSAJE","Venta registrada");
			
			
		} catch (Exception e) {
			redirec.addFlashAttribute("MENSAJE","Error en el registro");
			e.printStackTrace();
		}	
		return "redirect:/pagina-principal/finalizar";
	}
	
	@RequestMapping("/finalizar")
	public String finalizar(Model model, RedirectAttributes redirect) {		
		return "finalizar";
	}
}
