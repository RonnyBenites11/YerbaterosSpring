package pe.edu.cibertec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_Asiento")
public class Asiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cod_Asiento")
	private Integer codigo;
	
	@Column(name="Alias_Asiento")
	private String alias;
	
	@Column(name="Cod_Tipo_Asiento")
	private Integer codigoAsiento;
	
	@Column(name="Estado")
	private Integer estado;
	
	@ManyToOne
	@JoinColumn(name = "Cod_Viaje")
	private Viaje viaje;

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

	public Integer getCodigoAsiento() {
		return codigoAsiento;
	}

	public void setCodigoAsiento(Integer codigoAsiento) {
		this.codigoAsiento = codigoAsiento;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Asiento(Integer codigo) {
		super();
		this.codigo = codigo;
	}
	public Asiento() {
		
	}
}
