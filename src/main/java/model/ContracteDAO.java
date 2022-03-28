package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface ContracteDAO {
	
	static int Tots(List<Contracte> contractes) {
		return 0;	
	}
	
	int create(Connexio con, Contracte contracte);
	boolean update(Connexio con, Contracte contracte);
	boolean delete(Connexio con, int id);

}
