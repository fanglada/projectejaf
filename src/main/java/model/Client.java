package model;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Client extends Persona{
	
	private List<Carnet> carnets;

	public Client(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
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
	
	public boolean altaClient() {
		return false;
	}
	
	public boolean baixaClient() {
		return false;
	}
	
	public boolean modificarClient() {
		return false;
	}
}
