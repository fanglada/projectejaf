package model;

public class Conductor extends Persona {
	private int carnet;

	public Conductor(String dni, String nombre, String ape1, String ape2, String fNacimiento, String telefono,
			String direccion, String mail, int carnet) {
		super(dni, nombre, ape1, ape2, fNacimiento, telefono, direccion, mail);
		this.carnet = carnet;
	}

	public int getCarnet() {
		return carnet;
	}

	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}
	
}
