package pe.edu.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.time.LocalDate;



@Entity
@Table(name="tb_Viaje")
public class Viaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cod_Viaje")
	private Integer codigo;
	
	@Column(name="Fecha_Hora_Partida")
	private LocalDateTime fechaPartida;
	//private String fechaPartida;
	
	@Column(name="Fecha_Hora_Llegada")
	private LocalDateTime fechaLlegada;
	//private String fechaLlegada;
	
	@Column(name="Estado")
	private int estado;
	
	@ManyToOne
	@JoinColumn(name = "Cod_Ruta")
	private Ruta ruta;
	
	@ManyToOne
	@JoinColumn(name = "Cod_Bus")
	private Bus bus;

	@Column(name="Fecha_Partida")
	private String fechasPartida;
	//private String fechaPartida;
	
	@Column(name="Fecha_Llegada")
	private String fechasLlegada;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(LocalDateTime fechaPartida) {
		this.fechaPartida = fechaPartida;
	}

	public LocalDateTime getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
	public String getFechasPartida() {
		return fechasPartida;
	}

	public void setFechasPartida(String fechasPartida) {
		this.fechasPartida = fechasPartida;
	}

	public String getFechasLlegada() {
		return fechasLlegada;
	}

	public void setFechasLlegada(String fechasLlegada) {
		this.fechasLlegada = fechasLlegada;
	}
	
	public Viaje(Integer codigo) {
		super();
		this.codigo = codigo;
	}
	
	public Viaje() {
		
	}
	
	/*public String getFechaPartida() {
		return fechaPartida;
	}
	
	public void setFechaPartida(String fechaPartida) {
		this.fechaPartida = fechaPartida;
	}
	
	public String getFechaLlegada() {
		return fechaLlegada;
	}
	
	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}*/
	

}
