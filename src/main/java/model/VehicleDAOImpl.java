package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
				vehicles.add(new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"), new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio")),rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), rst.getDate("dataMatriculacio").toLocalDate(),rst.getInt("capacitat"), new Carnet(rst.getInt("idCarnet"),rst.getString("cdescripcio")),ParkingDAOImpl.BuscarVehicle(con, rst.getString("matricula"))));
			}
			
			return vehicles.size();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
	
	public static int Disponible(Connexio con, List<Vehicle> vehicles) {
		
		try {
			
			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT matricula,t.idTipus,t.descripcio,marca,model,CV,canvi,numeroRodes,numeroPortes,dataMatriculacio,capacitat,c.idCarnet, c.descripcio AS cdescripcio FROM vehicle v NATURAL JOIN tipusVehicle t INNER JOIN carnet c ON c.idCarnet=v.idCarnet;";
			
			ResultSet rst = stm.executeQuery(sql);

			while(rst.next())
			{
				vehicles.add(new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"), new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio")),rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), rst.getDate("dataMatriculacio").toLocalDate(),rst.getInt("capacitat"), new Carnet(rst.getInt("idCarnet"),rst.getString("cdescripcio")),ParkingDAOImpl.BuscarVehicle(con, rst.getString("matricula"))));
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
				vehicle = new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"), new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio")),rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), rst.getDate("dataMatriculacio").toLocalDate(),rst.getInt("capacitat"), new Carnet(rst.getInt("idCarnet"),rst.getString("cdescripcio")),ParkingDAOImpl.BuscarVehicle(con, rst.getString("matricula")));
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
				vehicle = new Vehicle(rst.getString("matricula"),rst.getString("marca"),rst.getString("model"), new TipusVehicle(rst.getInt("idTipus"),rst.getString("descripcio")),rst.getString("Canvi"), rst.getInt("CV"), rst.getInt("numeroRodes"), rst.getInt("numeroPortes"), rst.getDate("dataMatriculacio").toLocalDate(),rst.getInt("capacitat"), new Carnet(rst.getInt("idCarnet"),rst.getString("cdescripcio")),ParkingDAOImpl.BuscarVehicle(con, rst.getString("matricula")));
			}
			
			return vehicle;
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public static int BuscarParking(Connexio con, List<Vehicle> vehicles, int id) {
		
		try {

			String sql = "SELECT * FROM movimentParking NATURAL JOIN vehicle v NATURAL JOIN tipusVehicle INNER JOIN carnet c ON c.idCarnet=v.idCarnet WHERE desti="+id+";";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				vehicles.add(new Vehicle(resultSet.getString("matricula"), resultSet.getString("marca"), resultSet.getString("model"), new TipusVehicle(resultSet.getInt("idTipus"), resultSet.getString("decripcio")), resultSet.getString("canvi"), resultSet.getInt("cv"), resultSet.getInt("numRodes"), resultSet.getInt("numPortes"), resultSet.getDate("dataMatriculacio").toLocalDate(), resultSet.getInt("capacitat"),new Carnet(resultSet.getInt("idCarnet"),resultSet.getString("descripcio")),ParkingDAOImpl.select(con, resultSet.getInt("desti"))));
			}
			return vehicles.size();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static List<Vehicle> BuscarParking(Connexio con, int id) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {

			String sql = "SELECT * FROM movimentParking NATURAL JOIN vehicle v NATURAL JOIN tipusVehicle INNER JOIN carnet c ON c.idCarnet=v.idCarnet WHERE desti="+id+";";
			Statement stm = con.getConnexio().createStatement();
			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				vehicles.add(new Vehicle(resultSet.getString("matricula"), resultSet.getString("marca"), resultSet.getString("model"), new TipusVehicle(resultSet.getInt("idTipus"), resultSet.getString("descripcio")), resultSet.getString("canvi"), resultSet.getInt("cv"), resultSet.getInt("numeroRodes"), resultSet.getInt("numeroPortes"), resultSet.getDate("dataMatriculacio").toLocalDate(), resultSet.getInt("capacitat"),new Carnet(resultSet.getInt("idCarnet"),resultSet.getString("descripcio")),ParkingDAOImpl.select(con, resultSet.getInt("desti"))));
			}
			return vehicles;
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
			if(VehicleDAOImpl.select(con, vehicle.getMatricula())!= null)
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
	
			
				stm.executeUpdate();
				
				PreparedStatement stm2 = con.getConnexio().prepareStatement("INSERT INTO movimentParking VALUES (NULL,NULL,?,?,?)");				
				stm2.setInt(1, vehicle.getParking().getIdParking());
				stm2.setString(2, vehicle.getMatricula());
				stm2.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
	
				return stm2.executeUpdate();
			}
			return 0;
	
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
			stm.setInt(1, vehicle.getTipus().getIdTipusVehicle());
			stm.setString(2, vehicle.getMarca());
			stm.setString(3, vehicle.getModel());
			stm.setInt(4, vehicle.getCv());
			stm.setString(5, vehicle.getCanvi());
			stm.setInt(6, vehicle.getNumRodes());
			stm.setInt(7, vehicle.getNumPortes());
			stm.setDate(8, Date.valueOf(vehicle.getDataMatriculacio()));
			stm.setInt(9, vehicle.getCapacitat());
			stm.setInt(10, vehicle.getCarnet().getIdCarnet());
			stm.setString(11, vehicle.getMatricula());


			stm.executeUpdate();
			
			String sql = "SELECT desti FROM movimentParking WHERE matricula='"+vehicle.getMatricula()+"' ORDER BY dataHora DESC LIMIT 1 ;";
			Statement stm1 = con.getConnexio().createStatement();
			ResultSet rst = stm1.executeQuery(sql);
			int origen = 0;
			if(rst.next())
			{
				origen = rst.getInt("desti");
			}
			
			PreparedStatement stm2 = con.getConnexio().prepareStatement("INSERT INTO movimentParking VALUES (NULL,?,?,?,?)");				
			stm2.setInt(1, origen);
			stm2.setInt(2, vehicle.getParking().getIdParking());
			stm2.setString(3, vehicle.getMatricula());
			stm2.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

			return stm2.executeUpdate();
			
		
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
