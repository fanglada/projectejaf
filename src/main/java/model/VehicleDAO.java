package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface VehicleDAO {
	
	static int Tots(Connexio con, List<Vehicle> vehicles) {
		return 0;	
	}
	
	static int select(Connexio con, Vehicle vehicle, String id) {
		return 0;	
	}
	
	static Vehicle select(Connexio con, String id) {
		return null;	
	}
	
	int create(Connexio con, Vehicle vehicle);
	int update(Connexio con, Vehicle vehicle);
	int delete(Connexio con, String id);

}
