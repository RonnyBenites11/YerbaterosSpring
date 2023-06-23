package pe.edu.cibertec.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ventapasaje")
public class VentaPasaje implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_venta")
	private int idVenta;
	
	//@Temporal(TemporalType.DATE)
	@Column(name = "fecha_venta")
	private LocalDateTime fechaVenta;
	
	@Column(name = "monto_total")
	private double monto;
	
	@ManyToOne
	@JoinColumn(name = "cod_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "cod_viaje")
	private Viaje viaje;
	
	@OneToMany(mappedBy = "ventapasaje")
	private List<DetalleVentaPasaje> listaDetalleVentaPasaje;
	
	
	
	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public List<DetalleVentaPasaje> getListaDetalleVentaPasaje() {
		return listaDetalleVentaPasaje;
	}

	public void setListaDetalleVentaPasaje(List<DetalleVentaPasaje> listaDetalleVentaPasaje) {
		this.listaDetalleVentaPasaje = listaDetalleVentaPasaje;
	}
	
	
	public VentaPasaje(Integer idVenta) {
		super();
		this.idVenta = idVenta;
	}
	
	public VentaPasaje() {
		
	}
}
