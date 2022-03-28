package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dam2.jaf.Connexio;

public class CarnetDAOImpl implements CarnetDAO {

	static int Tots(Connexio con, List<Carnet> carnets) {

		int size=0;
		try {

			String sql = "Select * from carnet;";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				Carnet carnet = new Carnet(resultSet.getInt("idCarnet"), resultSet.getString("descripcio"));
				carnets.add(carnet);
			}
			size=carnets.size();
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
		return size;
	}

	@Override
	public int Create(Connexio con, Carnet carnet) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO Carnet VALUES (NULL,?)");				
			stm.setString(1, carnet.getDescripcio());

			stm.executeUpdate();

			resultat = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultat;
	}

	@Override
	public int Update(Connexio con, Carnet carnet) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE Carnet SET idCarnet = ?, descripcio = ?");				
			stm.setInt(1, carnet.getIdCarnet());
			stm.setString(2, carnet.getDescripcio());

			stm.executeUpdate();

			resultat = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultat;
	}


	@Override
	public int Delete(Connexio con, int id) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE from Carnet where idCarnet = ?");				
			stm.setInt(1, id);

			stm.executeUpdate();

			resultat = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultat;
	}

}
