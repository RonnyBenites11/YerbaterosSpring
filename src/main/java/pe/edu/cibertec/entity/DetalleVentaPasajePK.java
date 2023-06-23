package pe.edu.cibertec.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class DetalleVentaPasajePK implements Serializable{
	@Column(name = "id_venta")
	private int idVenta;
	
	@Column(name = "cod_asiento")
	private int codigoAsiento;
	
	
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public int getCodigoAsiento() {
		return codigoAsiento;
	}
	public void setCodigoAsiento(int codigoAsiento) {
		this.codigoAsiento = codigoAsiento;
	}
	
	@Override
	public int hashCode() {
		//return Objects.hash(idVenta, codigoAsiento);
		final int prime = 31;
		int result = 1;
		result = prime * result + idVenta;
		result = prime * result + codigoAsiento;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		DetalleVentaPasajePK other = (DetalleVentaPasajePK) obj;
		//return codigoAsiento == other.codigoAsiento && idVenta == other.idVenta;
		if (idVenta != other.idVenta)
			return false;
		if (codigoAsiento != other.codigoAsiento)
			return false;
		return true;
	}
}
