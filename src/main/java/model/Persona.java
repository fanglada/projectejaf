package model;

public class Persona {
	private String DNI;
	private String nombre;
	private String ape1;
	private String ape2;
	private String fNacimiento;
	private String telefono;
	private String direccion;
	private String mail;
	
	public Persona(String dNI, String nombre, String ape1, String ape2, String fNacimiento, String telefono,
			String direccion, String mail) {
		DNI = dNI;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.fNacimiento = fNacimiento;
		this.telefono = telefono;
		this.direccion = direccion;
		this.mail = mail;
	}
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApe1() {
		return ape1;
	}
	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}
	public String getApe2() {
		return ape2;
	}
	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}
	public String getfNacimiento() {
		return fNacimiento;
	}
	public void setfNacimiento(String fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
