package model;
import java.util.*;

import dam2.jaf.Connexio;
import model.MovimentParking;

public interface MovimentParkingDAO {

	static int Tots(Connexio con, List<MovimentParking> movimentParkings) {
		return 0;
	}
	
	int Create(Connexio con,MovimentParking movimentParking);
	int Update(Connexio con,MovimentParking movimentParking);
	int Delete(Connexio con, int id);
}