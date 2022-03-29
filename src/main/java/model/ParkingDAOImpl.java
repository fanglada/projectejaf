package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dam2.jaf.Connexio;

public class ParkingDAOImpl implements ParkingDAO {

	static int Tots(Connexio con, List<Parking> parkings) {
		try {

			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT * FROM parking";

			ResultSet rst = stm.executeQuery(sql);

			while (rst.next()) {
				parkings.add(new Parking(rst.getInt("idParking"),BotigaDAOImpl.select(con,rst.getInt("idParking")), rst.getString("telefon"), rst.getString("direccio"), rst.getString("descripcio"), rst.getInt("capacitat")));
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
				parking = new Parking(rst.getInt("idParking"),BotigaDAOImpl.select(con,rst.getInt("idParking")), rst.getString("telefon"), rst.getString("direccio"), rst.getString("descripcio"), rst.getInt("capacitat"));
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
				parking = new Parking(rst.getInt("idParking"),BotigaDAOImpl.select(con,rst.getInt("idParking")), rst.getString("telefon"), rst.getString("direccio"), rst.getString("descripcio"), rst.getInt("capacitat"));
			}

			return parking;

		} catch (SQLException e) {
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
