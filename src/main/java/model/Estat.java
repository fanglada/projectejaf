package model;

public class Estat {
	
	private int idEstat;
	private String descripcio;
	
	public Estat(int idEstat, String descripcio) {
		this.idEstat = idEstat;
		this.descripcio = descripcio;
	}
	
	public Estat(String descripcio) {
		this.descripcio = descripcio;
	}

	public int getIdEstat() {
		return idEstat;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	@Override
	public String toString() {
		return descripcio;
	}
	
	

}
