package model;


import java.util.List;
import dam2.jaf.Connexio;

public interface ClientDAO {
	
	static int Tots(List<Client> clients) {
		return 0;	
	}
	
	int create(Connexio con, Client clinet);
	boolean update(Connexio con, Client client);
	boolean delete(Connexio con, int id);
}
