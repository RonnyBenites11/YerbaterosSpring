package pe.edu.cibertec.entity;

public class UsuarioRegistroDTO {

	private int id;

	private String email;

	private String password;

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

	

	public UsuarioRegistroDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	

	public UsuarioRegistroDTO() {
		
	}

}
