package model;

import java.time.*;

public class MovimentParking {
	
	private int idMovimentParking;
	private Parking origen;
	private Parking desti;
	private Vehicle vehicle;
	private LocalDateTime dataHora;
	
	public MovimentParking(int idMovimentParking, Parking origen, Parking desti, Vehicle vehicle,
			LocalDateTime dataHora) {
		this.idMovimentParking = idMovimentParking;
		this.origen = origen;
		this.desti = desti;
		this.vehicle = vehicle;
		this.dataHora = dataHora;
	}
	
	public MovimentParking(Parking origen, Parking desti, Vehicle vehicle, LocalDateTime dataHora) {
		this.origen = origen;
		this.desti = desti;
		this.vehicle = vehicle;
		this.dataHora = dataHora;
	}
	
	public void setIdMovimentParking(int idMovimentParking) {
		this.idMovimentParking = idMovimentParking;
	}

	public int getIdMovimentParking() {
		return idMovimentParking;
	}

	public Parking getOrigen() {
		return origen;
	}

	public void setOrigen(Parking origen) {
		this.origen = origen;
	}

	public Parking getDesti() {
		return desti;
	}

	public void setDesti(Parking desti) {
		this.desti = desti;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "MovimentParking [origen=" + origen + ", desti=" + desti + ", vehicle=" + vehicle + "]";
	}
}
