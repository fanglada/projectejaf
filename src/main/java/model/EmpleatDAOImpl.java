package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import dam2.jaf.Connexio;

public class EmpleatDAOImpl implements EmpleatDAO {
	
	public static int Tots(Connexio con, List<Empleat> empleats) {
		try 
		{
			Connection conection = con.getConnexio();
			
			Statement stm = conection.createStatement();
			
			String sql = "SELECT * FROM treballador;";
			
			ResultSet rst = stm.executeQuery(sql);		
			
			while(rst.next()){
				empleats.add(new Empleat(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail")));
			}

			return empleats.size();
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int create(Connexio con, Empleat empleat) {
		try 
		{
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement("INSERT INTO treballador VALUES (?,?,?,?,?,?,?,?,NULL)");
			stm.setString(1, empleat.getDni());
			stm.setString(2, empleat.getNom());
			stm.setString(3, empleat.getCognom1());
			stm.setString(4, empleat.getCognom2());
			stm.setDate(5, Date.valueOf(empleat.getDataNaixament()));
			stm.setString(6, empleat.getTelefon());
			stm.setString(7, empleat.getDireccio());
			stm.setString(8, empleat.getMail());
			return stm.executeUpdate();			

		
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Connexio con, Empleat empleat) {
		try 
		{
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement("UPDATE treballador SET nom=?,cognom1=?,cognom2=?,dataNaixement=?,telefon=?,direccio=?,mail=? WHERE DNI=?");
			stm.setString(1, empleat.getNom());
			stm.setString(2, empleat.getCognom1());
			stm.setString(3, empleat.getCognom2());
			stm.setDate(4, Date.valueOf(empleat.getDataNaixament()));
			stm.setString(5, empleat.getTelefon());
			stm.setString(6, empleat.getDireccio());
			stm.setString(7, empleat.getMail());
			stm.setString(8, empleat.getDni());
			return stm.executeUpdate();			

		
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
						
			PreparedStatement stm = conection.prepareStatement("DELETE FROM treballador WHERE DNI=?");	
			
			stm.setString(1, id);
			
			return stm.executeUpdate();

		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

}
