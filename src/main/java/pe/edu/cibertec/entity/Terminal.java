package pe.edu.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_terminalterrestre")
public class Terminal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cod_Terminal")
	private Integer codigo;
	
	@Column(name="Nombre_Terminal")
	private String nombres;
	
	@Column(name="Estado")
	private int estado;
	
	@ManyToOne
	@JoinColumn(name = "Cod_Departamento")
	private Departamento departamento;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}	
}
