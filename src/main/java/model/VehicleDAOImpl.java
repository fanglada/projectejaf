package model;

import java.sql.*;
import java.util.List;

import dam2.jaf.Connexio;

public class VehicleDAOImpl implements VehicleDAO {
	
	public static int Tots(Connexio con, List<Vehicle> vehicles) {
		
		try {
			
			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT matricula,t.idTipus,t.descripcio,marca,model,CV,canvi,numeroRodes,numeroPortes,dataMatriculacio,capacitat,c.idCarnet, c.descripcio AS cdescripcio FROM vehicle v NATURAL JOIN tipusVehicle t INNER JOIN carnet c ON c.idCarnet=v.idCarnet;";
			
			ResultSet rst = stm.executeQuery(sql);

			while(rst.next())
			{
				vehicles.add(new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"), new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio")),rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), rst.getDate("dataMatriculacio").toLocalDate(),rst.getInt("capacitat"), new Carnet(rst.getInt("idCarnet"),rst.getString("cdescripcio"))));
			}
			
			return vehicles.size();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
	
	public static int select(Connexio con, Vehicle vehicle, String id) {
		try {
			
			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT matricula,t.idTipus,t.descripcio,marca,model,CV,canvi,numeroRodes,numeroPortes,dataMatriculacio,capacitat,c.idCarnet, c.descripcio AS cdescripcio FROM vehicle v NATURAL JOIN tipusVehicle t INNER JOIN carnet c ON c.idCarnet=v.idCarnet WHERE matricula='"+id+"';";
			
			ResultSet rst = stm.executeQuery(sql);

			if(rst.next())
			{
				vehicle = new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"), new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio")),rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), rst.getDate("dataMatriculacio").toLocalDate(),rst.getInt("capacitat"), new Carnet(rst.getInt("idCarnet"),rst.getString("cdescripcio")));
			}
			
			return 1;
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Vehicle select(Connexio con, String id) {
		Vehicle vehicle = null;
		try {
			
			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT matricula,t.idTipus,t.descripcio,marca,model,CV,canvi,numeroRodes,numeroPortes,dataMatriculacio,capacitat,c.idCarnet, c.descripcio AS cdescripcio FROM vehicle v NATURAL JOIN tipusVehicle t INNER JOIN carnet c ON c.idCarnet=v.idCarnet WHERE matricula='"+id+"';";
			
			ResultSet rst = stm.executeQuery(sql);

			if(rst.next())
			{
				vehicle = new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"), new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio")),rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), rst.getDate("dataMatriculacio").toLocalDate(),rst.getInt("capacitat"), new Carnet(rst.getInt("idCarnet"),rst.getString("cdescripcio")));
			}
			
			return vehicle;
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public int create(Connexio con, Vehicle vehicle) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO vehicle VALUES (?,?,?,?,?,?,?,?,?,?,?)");
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
			stm.setInt(11, vehicle.getCarnet().getIdCarnet());

		
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
			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE vehicle SET idTipus = ?, marca = ?, model = ?, cv = ?, canvi = ?, numeroRodes = ?, numeroPortes = ?, dataMatriculacio = ?, capacitat = ? , idCarnet = ? WHERE matricula = ? ");
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
			stm.setInt(11, vehicle.getCarnet().getIdCarnet());

		
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
