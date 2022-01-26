package model;

import java.time.LocalDate;

public class Supervisor extends Empleat {

	private String telefonEmpresa;

	public Supervisor(String dni, String nom, String cognom1, String cognom2, LocalDate dataNaixament, String telefon,
			String direccio, String mail,String telefonEmpresa) {
		super(dni, nom, cognom1, cognom2, dataNaixament, telefon, direccio, mail);
		this.telefonEmpresa = telefonEmpresa;
	}
	
	public String getTelefonEmpresa() {
		return telefonEmpresa;
	}

	public void setTelefonEmpresa(String telefonEmpresa) {
		this.telefonEmpresa = telefonEmpresa;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public boolean altaEmplet() {
		return false;
	}
	
	@Override
	public boolean baixaEmplet() {
		return false;
	}

	@Override
	public boolean modificarEmplet() {
		return false;
	}
}
