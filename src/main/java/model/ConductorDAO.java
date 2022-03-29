package model;

import java.util.*;
import dam2.jaf.Connexio;

public interface ConductorDAO {
	
	static int Tots(Connexio con, List<Conductor> conductors) {
		return 0;
	}
	
	static int select(Connexio con, Conductor conductor, String id) {
		return 0;	
	}
	
	static Conductor select(Connexio con, String id) {
		return null;	
	}
		
	int create(Connexio con, Conductor conductor);
	int update(Connexio con, Conductor conductor);
	int delete(Connexio con, String id);


}
