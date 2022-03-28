package model;

import java.sql.*;
import java.util.List;

import dam2.jaf.Connexio;

public class EstatDAOImpl implements EstatDAO {

	public static int Tots(Connexio con, List<Estat> estats) {

		try {

			String sql = "SELECT * FROM estat;";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				estats.add(new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")));
			}
			
			return estats.size();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int Create(Connexio con, Estat estat) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO Estat VALUES (NULL,?)");				
			stm.setString(1, estat.getDescripcio());

			resultat = stm.executeUpdate();
			
			ResultSet rst = stm.executeQuery("SELECT @@Identity AS id");
			
			if(rst.next())
			{
				estat.setIdEstat(rst.getInt("id"));
			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultat;
	}

	@Override
	public int Update(Connexio con, Estat estat) {

		int resultat = 0;

		try {

			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE Estat SET descripcio = ? WHERE idEstat = ?");				
			stm.setString(1, estat.getDescripcio());
			stm.setInt(2, estat.getIdEstat());


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

			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM Estat WHERE idEstat = ?");				
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
