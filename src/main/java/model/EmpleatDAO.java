package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface EmpleatDAO {
	
	static int Tots(Connexio con, List<Empleat> empleats) {
		return 0;	
	}
	
	static int select(Connexio con, Empleat empleat, int id) {
		return 0;	
	}
	
	static Empleat select(Connexio con, int id) {
		return null;	
	}
	
	int create(Connexio con, Empleat empleat);
	int update(Connexio con, Empleat empleat);
	int delete(Connexio con, String id);

}
