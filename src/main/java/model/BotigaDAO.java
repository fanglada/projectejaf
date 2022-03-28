package model;

import java.util.List;

import dam2.jaf.Connexio;

public interface BotigaDAO {

	static int Tots(Connexio con, List<Botiga> botigues) {
		return 0;
	}
		
	int create(Connexio con, Botiga botiga);
	int update(Connexio con, Botiga botiga);
	int delete(Connexio con, int id);

}
