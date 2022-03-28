package model;
import java.util.*;

import dam2.jaf.Connexio;
import model.Carnet;

public interface CarnetDAO {

	static int Tots(Connexio con, List<Carnet> carnets) {
		return 0;
	}
	
	int Create(Connexio con,Carnet carnet);
	int Update(Connexio con,Carnet carnet);
	int Delete(Connexio con, int id);
}