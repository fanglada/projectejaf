package model;
import java.util.*;

import dam2.jaf.Connexio;
import model.Extra;

public interface ExtraDAO {

	static int Tots(Connexio con, List<Extra> extras) {
		return 0;
	}
	
	int Create(Connexio con,Extra extra);
	int Update(Connexio con,Extra extra);
	int Delete(Connexio con, int id);
}