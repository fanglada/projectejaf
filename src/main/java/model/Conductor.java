package model;

import java.time.*;

public class Conductor extends Persona {
	
	private Carnet carnet;

	public Conductor(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
			String direccio, String mail,Carnet carnet) {
		super(dni, nom, cognom1, cognom2, dataNaixament, telefon, direccio, mail);
		this.carnet = carnet;
	}

	public Carnet getCarnet() {
		return carnet;
	}

	public void setCarnet(Carnet carnet) {
		this.carnet = carnet;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
