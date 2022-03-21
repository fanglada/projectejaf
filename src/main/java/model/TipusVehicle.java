package model;

public class TipusVehicle {
	
	private int idTipusVehicle;
	private String descripcio;
	
	public TipusVehicle(int idTipusVehicle, String descripcio) {
		this.idTipusVehicle = idTipusVehicle;
		this.descripcio = descripcio;
	}
	
	public TipusVehicle(String descripcio) {
		this.descripcio = descripcio;
	}
	
	public void setIdTipusVehicle(int idTipusVehicle) {
		this.idTipusVehicle = idTipusVehicle;
	}

	public int getIdTipusVehicle() {
		return idTipusVehicle;
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
	
	public boolean altaTipusVehicle() {
		return false;
	}

	public boolean baixaTipusVehicle() {
		return false;
	}

	public boolean modificarTipusVehicle() {
		return false;
	}
}
