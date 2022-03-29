package model;

import java.sql.*;
import java.util.List;

import dam2.jaf.Connexio;

public class VehicleDAOImpl implements VehicleDAO {
	
	public static int Tots(Connexio con, List<Vehicle> vehicles) {
		
		try {
			
			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT * FROM vehicle NATURAL JOIN tipusVehicle";
			
			ResultSet rst = stm.executeQuery(sql);

			while(rst.next())
			{
				TipusVehicle tipusVehicle = new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio"));
				vehicles.add(new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"),tipusVehicle,rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), rst.getDate("dataMatriculacio").toLocalDate(),rst.getInt("capacitat")));
			}
			
			return vehicles.size();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int create(Connexio con, Vehicle vehicle) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO vehicle VALUES (?,?,?,?,?,?,?,?,?,?)");
			stm.setString(1, vehicle.getMatricula());
			stm.setInt(2, vehicle.getTipus().getIdTipusVehicle());
			stm.setString(3, vehicle.getMarca());
			stm.setString(4, vehicle.getModel());
			stm.setInt(5, vehicle.getCv());
			stm.setString(6, vehicle.getCanvi());
			stm.setInt(7, vehicle.getNumRodes());
			stm.setInt(8, vehicle.getNumPortes());
			stm.setDate(9, Date.valueOf(vehicle.getDataMatriculacio()));
			stm.setInt(10, vehicle.getCapacitat());
		
			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int update(Connexio con, Vehicle vehicle) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE vehicle SET idTipus = ?, marca = ?, model = ?, cv = ?, canvi = ?, numeroRodes = ?, numeroPortes = ?, dataMatriculacio = ?, capacitat = ? WHERE matricula = ? ");
			stm.setString(1, vehicle.getMatricula());
			stm.setInt(2, vehicle.getTipus().getIdTipusVehicle());
			stm.setString(3, vehicle.getMarca());
			stm.setString(4, vehicle.getModel());
			stm.setInt(5, vehicle.getCv());
			stm.setString(6, vehicle.getCanvi());
			stm.setInt(7, vehicle.getNumRodes());
			stm.setInt(8, vehicle.getNumPortes());
			stm.setDate(9, Date.valueOf(vehicle.getDataMatriculacio()));
			stm.setInt(10, vehicle.getCapacitat());
		
			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int delete(Connexio con, String id) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM vehicle WHERE matricula = ? ");
			stm.setString(1, id);

			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
