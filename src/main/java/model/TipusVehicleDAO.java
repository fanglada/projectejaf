package model;
import java.util.*;

import dam2.jaf.Connexio;
import model.TipusVehicle;

public interface TipusVehicleDAO {

	static int Tots(Connexio con, List<TipusVehicle> tipusVehicles) {
		return 0;
	}
	
	int Create(Connexio con,TipusVehicle tipusVehicle);
	int Update(Connexio con,TipusVehicle tipusVehicle);
	int Delete(Connexio con, int id);
}