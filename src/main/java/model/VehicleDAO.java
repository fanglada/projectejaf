package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface VehicleDAO {
	
	static int Tots(List<Vehicle> vehicles) {
		return 0;	
	}
	
	int create(Connexio con, Vehicle vehicle);
	boolean update(Connexio con, Vehicle vehicle);
	boolean delete(Connexio con, int id);

}
