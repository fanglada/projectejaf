package model;
import java.util.*;

import dam2.jaf.Connexio;
import model.Estat;

public interface EstatDAO {

	static int Tots(Connexio con, List<Estat> estats) {
		return 0;
	}
	
	int Create(Connexio con,Estat estat);
	int Update(Connexio con,Estat estat);
	int Delete(Connexio con, int id);
}