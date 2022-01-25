package model;

import java.util.*;

public class Parking {
	
	private String idParking;
	private Botiga botiga;
	private String direccio;
	private String telefon;
	private String descripcio;
	private String capacitat;
	ArrayList<Vehicle> vehicles;
	
	public Parking(String idParking, Botiga botiga, String direccio, String telefon, String descripcio,
			String capacitat) {
		this.idParking = idParking;
		this.botiga = botiga;
		this.direccio = direccio;
		this.telefon = telefon;
		this.descripcio = descripcio;
		this.capacitat = capacitat;
		this.vehicles = new ArrayList<Vehicle>();
	}

	public String getIdParking() {
		return idParking;
	}

	public void setIdParking(String idParking) {
		this.idParking = idParking;
	}

	public Botiga getBotiga() {
		return botiga;
	}

	public void setBotiga(Botiga botiga) {
		this.botiga = botiga;
	}

	public String getDireccio() {
		return direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getCapacitat() {
		return capacitat;
	}

	public void setCapacitat(String capacitat) {
		this.capacitat = capacitat;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void addVehicles(Vehicle vehicle) {
		this.vehicles.add(vehicle);
	}
	
	public void delVehicles(Vehicle vehicle) {
		this.vehicles.remove(vehicle);
	}

	@Override
	public String toString() {
		return idParking;
	}
	
	public boolean altaParking() {
		return false;
	}
	
	public boolean baixaParking() {
		return false;
	}
	
	public boolean modificarParking() {
		return false;
	}
}
