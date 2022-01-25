package model;

import java.time.*;

public class Conductor extends Persona {
<<<<<<< HEAD
	private int carnet;

	public Conductor(String dni, String nombre, String ape1, String ape2, String fNacimiento, String telefono,
			String direccion, String mail, int carnet) {
		super(dni, nombre, ape1, ape2, fNacimiento, telefono, direccion, mail);
		this.carnet = carnet;
	}

	public int getCarnet() {
		return carnet;
	}

	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}
=======
	
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
	
>>>>>>> branch 'master' of git@github.com:fanglada/projectejaf.git
	
}
