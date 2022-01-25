package model;

import java.time.*;

public class Persona {
	
	private String dni;
	private String nom;
	private String cognom1;
	private String cognom2;
	private LocalDate dataNaixament;
	private String telefon;
	private String direccio;
	private String mail;
	

	public Persona(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
			String direccio, String mail) {
		this.dni = dni;
		this.nom = nom;
		this.cognom1 = cognom1;
		this.cognom2 = cognom2;
		this.dataNaixament = dataNaixament;
		this.telefon = telefon;
		this.direccio = direccio;
		this.mail = mail;
	}
	
	public String getDni() {

		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	@Override
	public String toString() {
		return nom + " " + cognom1 + " " + cognom2;
	}
	
	
}
