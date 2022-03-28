package model;

import java.sql.*;
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

	@Override
	public int Create(Connexio con, Carnet carnet) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO Carnet VALUES (NULL,?)");				
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

			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE Carnet SET descripcio = ? WHERE idCarnet = ?");				
			
			stm.setString(1, carnet.getDescripcio());
			stm.setInt(2, carnet.getIdCarnet());

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

			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM Carnet WHERE idCarnet = ?");				
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
