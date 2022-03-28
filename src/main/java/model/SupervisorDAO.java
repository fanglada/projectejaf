package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface SupervisorDAO {

	static int Tots(List<Supervisor> supervisors) {
		return 0;	
	}
	
	int create(Connexio con, Supervisor supervisor);
	boolean update(Connexio con, Supervisor supervisor);
	boolean delete(Connexio con, int id);
}
