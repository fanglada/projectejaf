package model;

public class Canvi {
	
	private int idCanvi;
	private String descripcio;
	
	public Canvi(int idCanvi, String descripcio) {
		this.idCanvi = idCanvi;
		this.descripcio = descripcio;
	}
	
	public Canvi(String descripcio) {
		this.descripcio = descripcio;
	}

	public int getIdCanvi() {
		return idCanvi;
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
	
	public boolean altaCanvi() {
		return false;
	}

	public boolean baixaCanvi() {
		return false;
	}
	
	public boolean modificarCanvi() {
		return false;
	}
}
