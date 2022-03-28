package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dam2.jaf.Connexio;

public class EstatDAOImpl implements EstatDAO {

	static int Tots(Connexio con, List<Estat> estats) {

		int size=0;
		try {

			String sql = "SELECT * FROM estat;";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				estats.add(new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")));
			}
			size=estats.size();
		}

		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
		return size;
	}
	
	@Override
	public int Create(Connexio con, Estat estat) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO Estat VALUES (NULL,?)");				
			stm.setString(1, estat.getDescripcio());

			resultat = stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultat;
	}

	@Override
	public int Update(Connexio con, Estat estat) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE Estat SET idEstat = ?, descripcio = ?");				
			stm.setInt(1, estat.getIdEstat());
			stm.setString(2, estat.getDescripcio());

			resultat = stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultat;
	}

	@Override
	public int Delete(Connexio con, int id) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM Estat WHERE idEstat = ?");				
			stm.setInt(1, id);

			resultat = stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultat;
	}

}
