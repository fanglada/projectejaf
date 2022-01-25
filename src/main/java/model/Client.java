package model;

import java.time.*;

public class Client extends Persona{

	public Client(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
			String direccio, String mail) {
		super(dni, nom, cognom1, cognom2, dataNaixament, telefon, direccio, mail);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
