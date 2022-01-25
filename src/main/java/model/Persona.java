package model;

import java.time.LocalDate;

public class Persona {
<<<<<<< HEAD
	private String dni;
	private String nombre;
	private String ape1;
	private String ape2;
	private String fNacimiento;
	private String telefono;
	private String direccion;
=======
	
	private String dni;
	private String nom;
	private String cognom1;
	private String cognom2;
	private LocalDate dataNaixament;
	private String telefon;
	private String direccio;
>>>>>>> branch 'master' of git@github.com:fanglada/projectejaf.git
	private String mail;
	
<<<<<<< HEAD
	public Persona(String dNI, String nombre, String ape1, String ape2, String fNacimiento, String telefono,
			String direccion, String mail) {
		dni = dni;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.fNacimiento = fNacimiento;
		this.telefono = telefono;
		this.direccion = direccion;
=======
	public Persona(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
			String direccio, String mail) {
		this.dni = dni;
		this.nom = nom;
		this.cognom1 = cognom1;
		this.cognom2 = cognom2;
		this.dataNaixament = dataNaixament;
		this.telefon = telefon;
		this.direccio = direccio;
>>>>>>> branch 'master' of git@github.com:fanglada/projectejaf.git
		this.mail = mail;
	}
	
<<<<<<< HEAD
	public String getDni() {
=======
	public String getDNI() {
>>>>>>> branch 'master' of git@github.com:fanglada/projectejaf.git
		return dni;
	}
<<<<<<< HEAD
	public void setDNI(String dNI) {
		dni = dni;
=======
	public void setDNI(String dni) {
		this.dni = dni;
>>>>>>> branch 'master' of git@github.com:fanglada/projectejaf.git
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nombre) {
		this.nom = nombre;
	}
	public String getCognom1() {
		return cognom1;
	}
	public void setCognom1(String ape1) {
		this.cognom1 = ape1;
	}
	public String getCognom2() {
		return cognom2;
	}
	public void setCognom2(String ape2) {
		this.cognom2 = ape2;
	}
	public LocalDate getDataNaixament() {
		return dataNaixament;
	}
	public void setfNacimiento(LocalDate dataNaixament) {
		this.dataNaixament = dataNaixament;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefono) {
		this.telefon = telefono;
	}
	public String getDireccio() {
		return direccio;
	}
	public void setDireccio(String direccion) {
		this.direccio = direccion;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
