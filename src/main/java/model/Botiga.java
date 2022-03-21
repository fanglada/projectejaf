package model;

public class Botiga {

	private int idBotiga;
	private String telefon;
	private String direccio;
	private String descripcio;

	public Botiga(int idBotiga, String telefon, String direccio, String descripcio) {
		this.idBotiga = idBotiga;
		this.telefon = telefon;
		this.direccio = direccio;
		this.descripcio = descripcio;
	}
	
	public Botiga(String telefon, String direccio, String descripcio) {
		this.telefon = telefon;
		this.direccio = direccio;
		this.descripcio = descripcio;
	}

	public int getIdBotiga() {
		return idBotiga;
	}

	public void setIdBotiga(int idBotiga) {
		this.idBotiga = idBotiga;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getDireccio() {
		return direccio;
	}

	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	@Override
	public String toString() {
		return direccio;
	}
	
	public boolean altaBotiga() {
		return false;
	}

	public boolean baixaBotiga() {
		return false;
	}
	
	public boolean modificarBotiga() {
		return false;
	}
}
