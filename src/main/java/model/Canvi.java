package model;

public class Canvi {
	
	private int idCanvi;
	private String descripcio;
	
	public Canvi(int idCanvi, String descripcio) {
		super();
		this.idCanvi = idCanvi;
		this.descripcio = descripcio;
	}

	public int getIdCanvi() {
		return idCanvi;
	}

	public void setIdCanvi(int idCanvi) {
		this.idCanvi = idCanvi;
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
