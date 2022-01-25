package model;

public class Botiga {

	private String idBotiga;
	private String telefon;
	private String direccio;
	private String descripcio;

	public Botiga(String idBotiga, String telefon, String direccio, String descripcio) {
		this.idBotiga = idBotiga;
		this.telefon = telefon;
		this.direccio = direccio;
		this.descripcio = descripcio;
	}

	public String getIdBotiga() {
		return idBotiga;
	}

	public void setIdBotiga(String idBotiga) {
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
		return idBotiga;
	}
}
