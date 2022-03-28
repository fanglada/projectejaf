package model;

import java.util.List;

import dam2.jaf.Connexio;

public interface ParkingDAO {
	
	static int Tots(Connexio con, List<Parking> parkings) {
		return 0;
	}
		
	int create(Connexio con, Parking parking);
	int update(Connexio con, Parking parking);
	int delete(Connexio con, int id);

}
