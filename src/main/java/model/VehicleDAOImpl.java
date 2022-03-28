package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dam2.jaf.Connexio;

public class VehicleDAOImpl implements VehicleDAO {
	
	static int Tots(Connexio con, List<Vehicle> vehicles) {
		
		try {
			
			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT * FROM vehicle NATURAL JOIN tipusVehicle";
			
			ResultSet rst = stm.executeQuery(sql);

			while(rst.next())
			{
//				TipusVehicle tipusVehicle = new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio"));
//				vehicles.add(new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"),tipusVehicle,rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), LocalDate.parse(rst.getString("dataMatriculacio")),rst.getInt("capacitat"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vehicles.size();
	}

	@Override
	public int create(Connexio con, Vehicle vehicle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Connexio con, Vehicle vehicle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Connexio con, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
