package pe.edu.cibertec.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_detalleventapasaje")
public class DetalleVentaPasaje implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	//@EmbeddedId
	//private DetalleVentaPasajePK pk;
		
	@ManyToOne
	@JoinColumn(name="id_venta")//, referencedColumnName = "id_venta",insertable = false,updatable =false)
	private VentaPasaje ventapasaje;//ASOCI.
	
	@ManyToOne
	@JoinColumn(name="cod_asiento")//, referencedColumnName = "cod_asiento",insertable = false,updatable =false)
	private Asiento asiento;//ASOCI.
	
	@Column(name = "costo_ticket")
	private double precio;
	
	@Column(name = "descuento")
	private double descuento;
	
	@Column(name = "sub_total")
	private double subtotal;

	@ManyToOne
	@JoinColumn(name = "Id_Cli")
	private Cliente cliente;

	
	/*public DetalleVentaPasajePK getPk() {
		return pk;
	}

	public void setPk(DetalleVentaPasajePK pk) {
		this.pk = pk;
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VentaPasaje getVentapasaje() {
		return ventapasaje;
	}

	public void setVentapasaje(VentaPasaje ventapasaje) {
		this.ventapasaje = ventapasaje;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
	
		
}
