package model;

public class Carnet {

	private int idCarnet;
	private String descripcio;
	
	public Carnet(int idCarnet, String descripcio) {
		super();
		this.idCarnet = idCarnet;
		this.descripcio = descripcio;
	}

	public int getIdCarnet() {
		return idCarnet;
	}

	public void setIdCarnet(int idCarnet) {
		this.idCarnet = idCarnet;
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
