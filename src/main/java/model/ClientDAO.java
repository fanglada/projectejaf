package model;


import java.util.List;
import dam2.jaf.Connexio;

public interface ClientDAO {
	
	static int Tots(Connexio con, List<Client> clients) {
		return 0;	
	}
	
	static int select(Connexio con, Client client, String id) {
		return 0;	
	}
	
	static Client select(Connexio con, String id) {
		return null;	
	}
	
	int create(Connexio con, Client client);
	int update(Connexio con, Client client);
	int delete(Connexio con, Client client);
}
