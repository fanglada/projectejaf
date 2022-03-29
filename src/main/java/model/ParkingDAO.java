package model;

import java.util.List;

import dam2.jaf.Connexio;

public interface ParkingDAO {
	
	static int Tots(Connexio con, List<Parking> parkings) {
		return 0;
	}
	
	static int select(Connexio con, Parking parking, String id) {
		return 0;	
	}
	
	static Parking select(Connexio con, String id) {
		return null;	
	}
		
	int create(Connexio con, Parking parking);
	int update(Connexio con, Parking parking);
	int delete(Connexio con, int id);

}
