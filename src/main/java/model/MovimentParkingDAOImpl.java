package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import dam2.jaf.Connexio;

public class MovimentParkingDAOImpl implements MovimentParkingDAO {

	public static int Tots(Connexio con, List<MovimentParking> movimentParkings) {

		int size=0;
		try {

			String sql = "SELECT * FROM movimentParking;";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				movimentParkings.add(new MovimentParking(resultSet.getInt("idMoviment"), ParkingDAOImpl.select(con, resultSet.getInt("origen")), ParkingDAOImpl.select(con, resultSet.getInt("desti")), VehicleDAOImpl.select(con, resultSet.getString("matricula")), resultSet.getTimestamp("dataHora").toLocalDateTime()));
			}
			size=movimentParkings.size();
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
		return size;
	}

	@Override
	public int Create(Connexio con, MovimentParking movimentParking) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO MovimentParking VALUES (?,?,?,?,?)");				
			stm.setInt(1, movimentParking.getIdMovimentParking());
			stm.setInt(2, movimentParking.getOrigen().getIdParking());
			stm.setInt(3, movimentParking.getDesti().getIdParking());
			stm.setString(4, movimentParking.getVehicle().getMatricula());
			stm.setTimestamp(5, Timestamp.valueOf(movimentParking.getDataHora()));

			resultat = stm.executeUpdate();

			ResultSet rst = stm.executeQuery("SELECT @@Identity AS id");

			if(rst.next())
			{
				movimentParking.setIdMovimentParking(rst.getInt("id"));
			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultat;
	}

	@Override
	public int Update(Connexio con, MovimentParking movimentParking) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE MovimentParking SET origen = ?, desti = ?, matricula = ?, dataHora = ? WHERE idMovimentParking = ?");				
			stm.setInt(1, movimentParking.getIdMovimentParking());
			stm.setInt(2, movimentParking.getOrigen().getIdParking());
			stm.setInt(3, movimentParking.getDesti().getIdParking());
			stm.setString(4, movimentParking.getVehicle().getMatricula());
			stm.setTimestamp(5, Timestamp.valueOf(movimentParking.getDataHora()));

			resultat = stm.executeUpdate();

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

			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM MovimentParking WHERE idMovimentParking = ?");				
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