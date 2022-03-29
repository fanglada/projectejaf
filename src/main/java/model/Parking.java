package model;

import java.util.*;

public class Parking {
	
	private int idParking;
	private Botiga botiga;
	private String direccio;
	private String telefon;
	private String descripcio;
	private int capacitat;
	private List<Vehicle> vehicles;
	
	public Parking(int idParking, Botiga botiga, String direccio, String telefon, String descripcio,
			int capacitat) {
		this.idParking = idParking;
		this.botiga = botiga;
		this.direccio = direccio;
		this.telefon = telefon;
		this.descripcio = descripcio;
		this.capacitat = capacitat;
		this.vehicles = new ArrayList<Vehicle>();
	}
	
	public Parking(Botiga botiga, String direccio, String telefon, String descripcio,
			int capacitat) {
		this.botiga = botiga;
		this.direccio = direccio;
		this.telefon = telefon;
		this.descripcio = descripcio;
		this.capacitat = capacitat;
		this.vehicles = new ArrayList<Vehicle>();
	}

	public int getIdParking() {
		return idParking;
	}

	public void setIdParking(int idParking) {
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

	public int getCapacitat() {
		return capacitat;
	}

	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
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
		return direccio;
	}
}
