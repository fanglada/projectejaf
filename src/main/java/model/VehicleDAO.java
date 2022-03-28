package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface VehicleDAO {
	
	static int Tots(Connexio con, List<Vehicle> vehicles) {
		return 0;	
	}
	
	int create(Connexio con, Vehicle vehicle);
	int update(Connexio con, Vehicle vehicle);
	int delete(Connexio con, int id);

}
