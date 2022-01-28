package model;

import java.time.*;

public class Contracte {
	
	private int idContracte;
	private Client client;
	private Empleat empleat;
	private Conductor conductor;
	private LocalDate dataInici;
	private LocalDate dataFi;
	private Estat estat;
	private Vehicle vehicle;
	
	public Contracte(int idContracte, Client client, Empleat empleat, Conductor conductor, LocalDate dataInici,
			LocalDate dataFi, Estat estat, Vehicle vehicle) {
		this.idContracte = idContracte;
		this.client = client;
		this.empleat = empleat;
		this.conductor = conductor;
		this.dataInici = dataInici;
		this.dataFi = dataFi;
		this.estat = estat;
		this.vehicle = vehicle;
	}
	
	public Contracte(Client client, Empleat empleat, Conductor conductor, LocalDate dataInici,
			LocalDate dataFi, Estat estat, Vehicle vehicle) {
		this.client = client;
		this.empleat = empleat;
		this.conductor = conductor;
		this.dataInici = dataInici;
		this.dataFi = dataFi;
		this.estat = estat;
		this.vehicle = vehicle;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Empleat getEmpleat() {
		return empleat;
	}

	public void setEmpleat(Empleat empleat) {
		this.empleat = empleat;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	public LocalDate getDataInici() {
		return dataInici;
	}

	public void setDataInici(LocalDate dataInici) {
		this.dataInici = dataInici;
	}

	public LocalDate getDataFi() {
		return dataFi;
	}

	public void setDataFi(LocalDate dataFi) {
		this.dataFi = dataFi;
	}

	public Estat getEstat() {
		return estat;
	}

	public void setEstat(Estat estat) {
		this.estat = estat;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getIdContracte() {
		return idContracte;
	}

	@Override
	public String toString() {
		return client.toString() + " " + vehicle.toString();
	}
	
	public boolean altaContracte() {
		return false;
	}
	
	public boolean baixaContracte() {
		return false;
	}
	
	public boolean modificarContracte() {
		return false;
	}
}
