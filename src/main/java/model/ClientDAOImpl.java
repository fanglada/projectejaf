package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import dam2.jaf.Connexio;

public class ClientDAOImpl implements ClientDAO {
	
	static int Tots(Connexio con, List<Client> clients) {
		
		try 
		{
			Connection conection = con.getConnexio();
			
			Statement stm = conection.createStatement();
			
			String sql = "SELECT * FROM client;";
			
			ResultSet rst = stm.executeQuery(sql);		
			
			while(rst.next()){
				clients.add(new Client(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), LocalDate.parse(rst.getString("dataNaixement")), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail")));
			}
			
			return clients.size();
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public int create(Connexio con, Client client) {
		
		try 
		{
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement("INSERT INTO client VALUES (?,?,?,?,?,?,?,?)");
			stm.setString(1, client.getDni());
			stm.setString(2, client.getNom());
			stm.setString(3, client.getCognom1());
			stm.setString(4, client.getCognom2());
			stm.setDate(5, java.sql.Date.valueOf(client.getDataNaixament()));
			stm.setString(6, client.getTelefon());
			stm.setString(7, client.getDireccio());
			stm.setString(8, client.getMail());
			return stm.executeUpdate();			

		
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
			PreparedStatement stm = conection.prepareStatement("UPDATE client SET DNI=?,nom=?,cognom1=?,cognom2=?,dataNaixement=?,telefon=?,direccio=?,mail=?");
			stm.setString(1, client.getDni());
			stm.setString(2, client.getNom());
			stm.setString(3, client.getCognom1());
			stm.setString(4, client.getCognom2());
			stm.setDate(5, java.sql.Date.valueOf(client.getDataNaixament()));
			stm.setString(6, client.getTelefon());
			stm.setString(7, client.getDireccio());
			stm.setString(8, client.getMail());
			return stm.executeUpdate();			

		
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Connexio con, int id) {
		try 
		{
			Connection conection = con.getConnexio();
			
			Statement stm = conection.createStatement();
			
			return stm.executeUpdate("DELETE FROM client WHERE id="+id+"");						

		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

}
