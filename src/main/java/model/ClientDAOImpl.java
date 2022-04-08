package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import dam2.jaf.Connexio;

public class ClientDAOImpl implements ClientDAO {
	
	public static int Tots(Connexio con, List<Client> clients) {
		try 
		{

			Connection conection = con.getConnexio();
			
			Statement stm = conection.createStatement();
			
			String sql = "SELECT * FROM client;";
			
			ResultSet rst = stm.executeQuery(sql);		
			
			while(rst.next()){
				
				clients.add(new Client(rst.getString("Dni"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail"),CarnetDAOImpl.BuscarClient(con, rst.getString("DNI"))));
			}
			

			return clients.size();
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}
	
	public static int select(Connexio con, Client client, String id) {
		try 
		{
			Connection conection = con.getConnexio();
			
			Statement stm = conection.createStatement();
			
			String sql = "SELECT * FROM client WHERE DNI='"+id+"';";
			
			ResultSet rst = stm.executeQuery(sql);		
			
			if(rst.next()){
				
				client = new Client(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail"),CarnetDAOImpl.BuscarClient(con, rst.getString("DNI")));
			
			}

			return 1;
			
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}	
	}	
	
	
	public static Client select(Connexio con, String id) {
		
		Client client = null;
		try 
		{
			Connection conection = con.getConnexio();
			
			Statement stm = conection.createStatement();
			
			String sql = "SELECT * FROM client WHERE DNI='"+id+"';";
			
			ResultSet rst = stm.executeQuery(sql);		
			
			if(rst.next()){
				
				client = new Client(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail"),CarnetDAOImpl.BuscarClient(con, rst.getString("DNI")));
			}

			return client;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public int create(Connexio con, Client client) {
		
		try 
		{
			
			if(ClientDAOImpl.select(con, client.getDni())!= null)
			{
				Connection conection = con.getConnexio();
				PreparedStatement stm = conection.prepareStatement("INSERT INTO client VALUES (?,?,?,?,?,?,?,?)");
				stm.setString(1, client.getDni());
				stm.setString(2, client.getNom());
				stm.setString(3, client.getCognom1());
				stm.setString(4, client.getCognom2());
				stm.setDate(5, Date.valueOf(client.getDataNaixament()));
				stm.setString(6, client.getTelefon());
				stm.setString(7, client.getDireccio());
				stm.setString(8, client.getMail());
				int aux = stm.executeUpdate();
				int aux2 = 1;
				
				for(int i = 0; i < client.getCarnet().size(); i++) 
				{
					stm = conection.prepareStatement("INSERT INTO ClientCarnet VALUES (?,?)");
					stm.setInt(1, client.getCarnet().get(i).getIdCarnet());
					stm.setString(2, client.getDni());
					if (stm.executeUpdate() <= 0) 
					{
						aux2 = 0;
					}
				}	
	
				if (aux > 0 && aux2 > 0) 
				{
					return 1;
				}else 
				{
					return 0;
				}
			}
			
			return 0;
		
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Connexio con, Client client) {
		try 
		{
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement("UPDATE client SET nom=?,cognom1=?,cognom2=?,dataNaixement=?,telefon=?,direccio=?,mail=? WHERE DNI=?");
			stm.setString(1, client.getNom());
			stm.setString(2, client.getCognom1());
			stm.setString(3, client.getCognom2());
			stm.setDate(4, Date.valueOf(client.getDataNaixament()));
			stm.setString(5, client.getTelefon());
			stm.setString(6, client.getDireccio());
			stm.setString(7, client.getMail());
			stm.setString(8, client.getDni());
			int aux = stm.executeUpdate();
			int aux2 = 1, aux3 = 1;
			
			stm = conection.prepareStatement("DELETE FROM ClientCarnet WHERE DNIClient=?");
			stm.setString(1, client.getDni());
			aux2 = stm.executeUpdate();
			
			for(int i = 0; i < client.getCarnet().size(); i++) 
			{
				stm = conection.prepareStatement("INSERT INTO ClientCarnet VALUES (?,?)");
				stm.setInt(1, client.getCarnet().get(i).getIdCarnet());
				stm.setString(2, client.getDni());
				if (stm.executeUpdate() <= 0) 
				{
					aux3 = 0;
				}
			}	

			if (aux > 0 && aux2 > 0 && aux3 > 0) 
			{
				return 1;
			}else 
			{
				return 0;
			}			

		
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Connexio con, String id) {
		try 
		{
			Connection conection = con.getConnexio();
			
			PreparedStatement stm = conection.prepareStatement("DELETE FROM ClientCarnet WHERE DNIClient=?");
			stm.setString(1, id);
			int aux = stm.executeUpdate(), aux2 = 1;
						
			stm = conection.prepareStatement("DELETE FROM client WHERE DNI=?");	
			
			stm.setString(1, id);
			
			aux2 = stm.executeUpdate();
			
			if (aux > 0 && aux2 > 0) 
			{
				return 1;
			}else 
			{
				return 0;
			}

		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

}
