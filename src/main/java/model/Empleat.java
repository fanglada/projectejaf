package model;

import java.time.*;

public class Empleat extends Persona{
	
	private Botiga botiga;

	public Empleat(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
			String direccio, String mail,Botiga botiga) {
		super(dni, nom, cognom1, cognom2, dataNaixament, telefon, direccio, mail);
		this.botiga = botiga;
		// TODO Auto-generated constructor stub
	}
	

	public Botiga getBotiga() {
		return botiga;
	}



	public void setBotiga(Botiga botiga) {
		this.botiga = botiga;
	}



	@Override
	public String toString() {
		return super.toString();
	}
}
