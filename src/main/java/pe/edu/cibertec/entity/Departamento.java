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
@Table(name="tb_Departamento")
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Cod_Departamento")
	private Integer codigo;
	
	@Column(name="Nombre_Departamento")
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "departamento")
	private List<Terminal> listaTerminales;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Terminal> getListaTerminales() {
		return listaTerminales;
	}

	public void setListaTerminales(List<Terminal> listaTerminales) {
		this.listaTerminales = listaTerminales;
	}		
}
