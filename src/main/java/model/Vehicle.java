package model;

import java.time.*;
import java.util.*;

public class Vehicle {
	
	private String matricula;
	private String marca;
	private String model;
	private TipusVehicle tipus;
	private String canvi;
	private int cv;
	private int numRodes;
	private int numPortes;
	private LocalDate dataMatriculacio;
	private int capacitat;
	
	public Vehicle(String matricula, String marca, String model, TipusVehicle tipus, String canvi, int cv, int numRodes,
			int numPortes, LocalDate dataMatriculacio, int capacitat) {
		this.matricula = matricula;
		this.marca = marca;
		this.model = model;
		this.tipus = tipus;
		this.canvi = canvi;
		this.cv = cv;
		this.numRodes = numRodes;
		this.numPortes = numPortes;
		this.dataMatriculacio = dataMatriculacio;
		this.capacitat = capacitat;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public TipusVehicle getTipus() {
		return tipus;
	}
	
	public void setTipus(TipusVehicle tipus) {
		this.tipus = tipus;
	}
	
	public String getCanvi() {
		return canvi;
	}
	
	public void setCanvi(String canvi) {
		this.canvi = canvi;
	}
	
	public int getCv() {
		return cv;
	}
	
	public void setCv(int cv) {
		this.cv = cv;
	}
	
	public int getNumRodes() {
		return numRodes;
	}
	
	public void setNumRodes(int numRodes) {
		this.numRodes = numRodes;
	}
	
	public int getNumPortes() {
		return numPortes;
	}
	
	public void setNumPortes(int numPortes) {
		this.numPortes = numPortes;
	}
	
	public LocalDate getDataMatriculacio() {
		return dataMatriculacio;
	}
	
	public void setDataMatriculacio(LocalDate dataMatriculacio) {
		this.dataMatriculacio = dataMatriculacio;
	}
	
	public int getCapacitat() {
		return capacitat;
	}
	
	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}
	
	@Override
	public String toString() {
		return matricula;
	}
}
