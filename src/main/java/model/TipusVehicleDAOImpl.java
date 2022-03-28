package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dam2.jaf.Connexio;

public class TipusVehicleDAOImpl implements TipusVehicleDAO {

	public static int Tots(Connexio con, List<TipusVehicle> tipusVehicles) {

		try {

			String sql = "SELECT * FROM tipusVehicle;";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				tipusVehicles.add(new TipusVehicle(resultSet.getInt("idTipusVehicle"), resultSet.getString("descripcio")));
			}
			return tipusVehicles.size();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int Create(Connexio con, TipusVehicle tipusVehicle) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO TipusVehicle VALUES (NULL,?)");				
			stm.setString(1, tipusVehicle.getDescripcio());

			resultat = stm.executeUpdate();
			
			ResultSet rst = stm.executeQuery("SELECT @@Identity AS id");
			
			if(rst.next())
			{
				tipusVehicle.setIdTipusVehicle(rst.getInt("id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultat;
	}

	@Override
	public int Update(Connexio con, TipusVehicle tipusVehicle) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE TipusVehicle SET descripcio = ? WHERE idTipusVehicle = ?");				
			stm.setString(1, tipusVehicle.getDescripcio());
			stm.setInt(2, tipusVehicle.getIdTipusVehicle());


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

			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM TipusVehicle WHERE idTipusVehicle = ?");				
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