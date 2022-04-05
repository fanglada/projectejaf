package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dam2.jaf.Connexio;

public class CarnetDAOImpl implements CarnetDAO {

	public static int Tots(Connexio con, List<Carnet> carnets) {

		try {

			String sql = "SELECT * FROM carnet;";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				carnets.add(new Carnet(resultSet.getInt("idCarnet"), resultSet.getString("descripcio")));
			}
			return carnets.size();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int BuscarClient(Connexio con, List<Carnet> carnets, String id) {
		
		try {

			String sql = "SELECT * FROM carnet NATURAL JOIN ClientCarnet WHERE DNI ="+id+";";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				carnets.add(new Carnet(resultSet.getInt("idCarnet"), resultSet.getString("descripcio")));
			}
			return carnets.size();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static List<Carnet> BuscarClient(Connexio con, String id) {
		List<Carnet> carnets = new ArrayList<Carnet>();
		try {

			String sql = "SELECT * FROM carnet NATURAL JOIN ClientCarnet WHERE DNIClient ='"+id+"';";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				carnets.add(new Carnet(resultSet.getInt("idCarnet"), resultSet.getString("descripcio")));
			}
			return carnets;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static int BuscarConductor(Connexio con, List<Carnet> carnets, String id) {
	
		try {

			String sql = "SELECT * FROM carnet NATURAL JOIN conductorCarnet WHERE DNI ="+id+";";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				carnets.add(new Carnet(resultSet.getInt("idCarnet"), resultSet.getString("descripcio")));
			}
			return carnets.size();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static List<Carnet> BuscarConductor(Connexio con, String id) {
		List<Carnet> carnets = new ArrayList<Carnet>();
		try {

			String sql = "SELECT * FROM carnet NATURAL JOIN conductorCarnet WHERE DNI ="+id+";";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				carnets.add(new Carnet(resultSet.getInt("idCarnet"), resultSet.getString("descripcio")));
			}
			return carnets;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int Create(Connexio con, Carnet carnet) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO carnet VALUES (NULL,?)");				
			stm.setString(1, carnet.getDescripcio());

			resultat = stm.executeUpdate();
			
			ResultSet rst = stm.executeQuery("SELECT @@Identity AS id");
			
			if(rst.next())
			{
				carnet.setIdCarnet(rst.getInt("id"));
			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultat;
	}

	@Override
	public int Update(Connexio con, Carnet carnet) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE carnet SET descripcio = ? WHERE idCarnet = ?");				
			
			stm.setString(1, carnet.getDescripcio());
			stm.setInt(2, carnet.getIdCarnet());

			resultat = stm.executeUpdate();
			System.out.println(resultat);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultat;
	}


	@Override
	public int Delete(Connexio con, int id) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM carnet WHERE idCarnet = ?");				
			stm.setInt(1, id);

			resultat = stm.executeUpdate();

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultat;
	}

}
