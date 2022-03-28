package model;

import java.util.*;
import dam2.jaf.Connexio;

public interface ConductorDAO {
	
	static int Tots(Connexio con, List<Conductor> conductors) {
		return 0;
	}
		
	int create(Connexio con, Conductor conductor);
	int update(Connexio con, Conductor conductor);
	int delete(Connexio con, int id);


}
