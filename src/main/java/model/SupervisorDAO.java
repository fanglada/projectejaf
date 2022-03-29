package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface SupervisorDAO {

	static int Tots(Connexio con, List<Supervisor> supervisors) {
		return 0;	
	}
	
	//SELECT?
	
	int create(Connexio con, Supervisor supervisor);
	int update(Connexio con, Supervisor supervisor);
	int delete(Connexio con, String id);
}
