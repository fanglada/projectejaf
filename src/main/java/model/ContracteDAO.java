package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface ContracteDAO {
	
	static int Tots(Connexio con, List<Contracte> contractes) {
		return 0;	
	}
	
	int create(Connexio con, Contracte contracte);
	int update(Connexio con, Contracte contracte);
	int delete(Connexio con, int id);

}
