package model;


import java.util.List;
import dam2.jaf.Connexio;

public interface ClientDAO {
	
	static int Tots(Connexio con, List<Client> clients) {
		return 0;	
	}
	
	int create(Connexio con, Client client);
	int update(Connexio con, Client client);
	int delete(Connexio con, String id);
}
