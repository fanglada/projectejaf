package model;

public class Extra {
	
	private int idExtra;
	private String descripcio;
	
	public Extra(int idExtra, String descripcio) {
		this.idExtra = idExtra;
		this.descripcio = descripcio;
	}
	
	public Extra(String descripcio) {
		this.descripcio = descripcio;
	}

	public int getIdExtra() {
		return idExtra;
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
	
	public boolean altaExtra() {
		return false;
	}

	public boolean baixaExtra() {
		return false;
	}

	public boolean modificarExtra() {
		return false;
	}
}
