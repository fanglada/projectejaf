package model;

import java.util.List;
import dam2.jaf.Connexio;

public interface EmpleatDAO {
	
	static int Tots(List<Empleat> empleats) {
		return 0;	
	}
	
	int create(Connexio con, Empleat empleat);
	boolean update(Connexio con, Empleat empleat);
	boolean delete(Connexio con, int id);

}
