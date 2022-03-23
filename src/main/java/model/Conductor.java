package model;

import java.time.*;
import java.util.*;


public class Conductor extends Persona {
	
	private List<Carnet> carnets;

	public Conductor(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
			String direccio, String mail) {
		super(dni, nom, cognom1, cognom2, dataNaixament, telefon, direccio, mail);
		this.carnets = new ArrayList<Carnet>();
	}

	public List<Carnet> getCarnet() {
		return carnets;
	}

	public void setCarnet(List<Carnet> carnets) {
		this.carnets = carnets;
	}
	
	public void addVehicles(Carnet carnet) {
		this.carnets.add(carnet);
	}
	
	public void delVehicles(Carnet carnet) {
		this.carnets.remove(carnet);
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	public boolean altaConductor() {
		return false;
	}

	public boolean baixaConductor() {
		return false;
	}

	public boolean modificarConductor() {
		return false;
	}
}
