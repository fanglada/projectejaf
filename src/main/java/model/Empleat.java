package model;

import java.time.*;

public class Empleat extends Persona{

	public Empleat(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
			String direccio, String mail) {
		super(dni, nom, cognom1, cognom2, dataNaixament, telefon, direccio, mail);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	public boolean altaEmplet() {
		return false;
	}
	
	public boolean baixaEmplet() {
		return false;
	}

	public boolean modificarEmplet() {
		return false;
	}
}
