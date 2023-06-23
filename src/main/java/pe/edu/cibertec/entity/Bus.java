package pe.edu.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_Bus")
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cod_Bus")
	private Integer codigo;
	
	@Column(name="Registro")
	private String registro;
	
	@Column(name="Cantidad_Asientos")
	private Integer ctdAsientos;
	
	@Column(name="Marca")
	private String marca;
	
	@Column(name="Modelo")
	private String modelo;
	
	@Column(name="Estado")
	private Integer estado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bus")
	private List<Viaje> listaViajes;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Integer getCtdAsientos() {
		return ctdAsientos;
	}

	public void setCtdAsientos(Integer ctdAsientos) {
		this.ctdAsientos = ctdAsientos;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<Viaje> getListaViajes() {
		return listaViajes;
	}

	public void setListaViajes(List<Viaje> listaViajes) {
		this.listaViajes = listaViajes;
	}	
}
