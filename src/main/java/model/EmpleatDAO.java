package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface EmpleatDAO {
	
	static int Tots(Connexio con, List<Empleat> empleats) {
		return 0;	
	}
	
	int create(Connexio con, Empleat empleat);
	int update(Connexio con, Empleat empleat);
	int delete(Connexio con, int id);

}
