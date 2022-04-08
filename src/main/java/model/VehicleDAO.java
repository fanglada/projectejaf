package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dam2.jaf.Connexio;

public interface VehicleDAO {
	
	static int Tots(Connexio con, List<Vehicle> vehicles) {
		return 0;	
	}
	
	static int Disponible(Connexio con, List<Vehicle> vehicles) {
			return 0;
	}
	
	
	static int select(Connexio con, Vehicle vehicle, String id) {
		return 0;	
	}
	
	static Vehicle select(Connexio con, String id) {
		return null;	
	}
	
	static int BuscarParking(Connexio con, List<Vehicle> vehicles, String id) {
		return 0;
	}
	
	static List<Vehicle> BuscarParking(Connexio con, String id) {
		return null;
	}
	
	int create(Connexio con, Vehicle vehicle);
	int update(Connexio con, Vehicle vehicle);
	int delete(Connexio con, String id);

}
