package pe.edu.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_Ruta")
public class Ruta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cod_Ruta")
	private Integer codigo;
	
	@Column(name="Alias_Ruta")
	private String alias;
	
	@Column(name="Tiempo_Promedio_Ruta")
	private int tiempoRuta;
	
	@Column(name="Distanciakm")
	private int distancia;
	
	@Column(name="Estado")
	private int estado;
	
	@Column(name="Precio_Base")
	private float precio;
			
	@ManyToOne
	@JoinColumn(name = "Cod_Terminal_Origen")
	private Terminal terminalOrigen;
	
	@ManyToOne
	@JoinColumn(name = "Cod_Terminal_Destino")
	private Terminal terminalDestino;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getTiempoRuta() {
		return tiempoRuta;
	}

	public void setTiempoRuta(int tiempoRuta) {
		this.tiempoRuta = tiempoRuta;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Terminal getTerminalOrigen() {
		return terminalOrigen;
	}

	public void setTerminalOrigen(Terminal terminalOrigen) {
		this.terminalOrigen = terminalOrigen;
	}

	public Terminal getTerminalDestino() {
		return terminalDestino;
	}

	public void setTerminalDestino(Terminal terminalDestino) {
		this.terminalDestino = terminalDestino;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}	
}
