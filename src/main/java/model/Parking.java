package model;

import java.util.*;

public class Parking {
	
	private int idParking;
	private Botiga botiga;
	private String direccio;
	private String telefon;
	private String descripcio;
	private int capacitat;
	
	public Parking(int idParking, Botiga botiga, String direccio, String telefon, String descripcio,
			int capacitat) {
		this.idParking = idParking;
		this.botiga = botiga;
		this.direccio = direccio;
		this.telefon = telefon;
		this.descripcio = descripcio;
		this.capacitat = capacitat;
	}
	
	public Parking(Botiga botiga, String direccio, String telefon, String descripcio,
			int capacitat) {
		this.botiga = botiga;
		this.direccio = direccio;
		this.telefon = telefon;
		this.descripcio = descripcio;
		this.capacitat = capacitat;
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

	@Override
	public String toString() {
		return direccio;
	}
}
