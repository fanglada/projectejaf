package model;

public class Carnet {

	private int idCarnet;
	private String descripcio;
	
	public Carnet(int idCarnet, String descripcio) {
		this.idCarnet = idCarnet;
		this.descripcio = descripcio;
	}
	
	public Carnet(String descripcio) {
		this.descripcio = descripcio;
	}

	public void setIdCarnet(int idCarnet) {
		this.idCarnet = idCarnet;
	}

	public int getIdCarnet() {
		return idCarnet;
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
