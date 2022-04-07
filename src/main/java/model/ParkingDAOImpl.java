package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dam2.jaf.Connexio;

public class ParkingDAOImpl implements ParkingDAO {

	public static int Tots(Connexio con, List<Parking> parkings) {
		try {

			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT * FROM parking";

			ResultSet rst = stm.executeQuery(sql);

			while (rst.next()) {
				parkings.add(new Parking(rst.getInt("idParking"),BotigaDAOImpl.select(con,rst.getInt("idParking")), rst.getString("direccio"), rst.getString("telefon"), rst.getString("descripcio"), rst.getInt("capacitat")));
			}

			return parkings.size();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public static int select(Connexio con, Parking parking, int id) {
		try {

			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT * FROM parking WHERE idParking = "+id+";";

			ResultSet rst = stm.executeQuery(sql);

			while (rst.next()) {
				parking = new Parking(rst.getInt("idParking"),BotigaDAOImpl.select(con,rst.getInt("idParking")), rst.getString("direccio"), rst.getString("telefon"), rst.getString("descripcio"), rst.getInt("capacitat"));
			}

			return 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public static Parking select(Connexio con, int id) {
		Parking parking = null;
		try {

			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT * FROM parking WHERE idParking = "+id+";";

			ResultSet rst = stm.executeQuery(sql);

			while (rst.next()) {
				parking = new Parking(rst.getInt("idParking"),BotigaDAOImpl.select(con,rst.getInt("idParking")), rst.getString("direccio"), rst.getString("telefon"),rst.getString("descripcio"), rst.getInt("capacitat"));
			}

			return parking;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static int BuscarVehicle(Connexio con, Parking parking, String id) {
		
		try {

			String sql = "SELECT * FROM movimentParking NATURAL JOIN vehicle v NATURAL JOIN tipusVehicle INNER JOIN carnet c ON c.idCarnet=v.idCarnet WHERE desti="+id+";";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			if(resultSet.next()) {
				parking = new Parking(resultSet.getInt("idParking"), BotigaDAOImpl.select(con, resultSet.getInt("idBotiga")), resultSet.getString("telefon"), resultSet.getString("direccio"), resultSet.getString("descripcio"), resultSet.getInt("capacitat"));
			}
			return 1;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Parking BuscarVehicle(Connexio con, String id) {
		Parking parking = null;
		try {

			String sql = "SELECT * FROM movimentParking NATURAL JOIN vehicle v NATURAL JOIN tipusVehicle INNER JOIN carnet c ON c.idCarnet=v.idCarnet INNER JOIN parking p ON p.idParking=desti WHERE matricula='"+id+"';";
			Statement stm = con.getConnexio().createStatement();
			ResultSet resultSet= stm.executeQuery(sql);

			if(resultSet.next()) {
				parking = new Parking(resultSet.getInt("desti"), BotigaDAOImpl.select(con, resultSet.getInt("desti")), resultSet.getString("telefon"), resultSet.getString("direccio"), resultSet.getString("descripcio"), resultSet.getInt("capacitat"));
			}
			return parking;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int create(Connexio con, Parking parking) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO parking VALUES (?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, parking.getIdParking());
			stm.setString(2, parking.getTelefon());
			stm.setString(3, parking.getDireccio());
			stm.setString(4, parking.getDescripcio());

			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Connexio con, Parking parking) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE parking SET telefon = ?, direccio = ?, descripcio = ? WHERE idParking = ? ");
			stm.setInt(1, parking.getIdParking());
			stm.setString(2, parking.getTelefon());
			stm.setString(3, parking.getDireccio());
			stm.setString(4, parking.getDescripcio());
			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int delete(Connexio con, int id) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM parking WHERE idParking = ? ");
			stm.setInt(1, id);

			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
