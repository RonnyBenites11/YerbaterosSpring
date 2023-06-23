package pe.edu.cibertec.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "tb_usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UsuarioTA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	
	private String email;
	
	private String password;
	
	
 @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL )
 @JoinTable(
		 name = "usuarios_roles",
		 joinColumns = @JoinColumn
		 (
				name = "usuario_id" , referencedColumnName = "id"),
		 inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id" )
		 
		 )
 
	private Collection<Rol> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	public UsuarioTA(int id, String email, String password, Collection<Rol> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public UsuarioTA(String email, String password, Collection<Rol> roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public UsuarioTA() {
	
	}

}
