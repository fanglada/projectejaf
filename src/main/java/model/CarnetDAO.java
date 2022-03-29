package model;
import java.util.*;

import dam2.jaf.Connexio;
import model.Carnet;

public interface CarnetDAO {

	static int Tots(Connexio con, List<Carnet> carnets) {
		return 0;
	}
	
	static int BuscarClient(Connexio con, List<Carnet> carnets,String id) {
		return 0;
	}
	
	static List<Carnet> BuscarClient(Connexio con, String id) {
		return null;
	}
	
	static int BuscarConductor(Connexio con, List<Carnet> carnets,String id) {
		return 0;
	}
	
	static List<Carnet> BuscarConductor(Connexio con, String id) {
		return null;
	}
	
	int Create(Connexio con,Carnet carnet);
	int Update(Connexio con,Carnet carnet);
	int Delete(Connexio con, int id);
}