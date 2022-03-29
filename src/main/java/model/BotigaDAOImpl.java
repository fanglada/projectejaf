package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dam2.jaf.Connexio;

public class BotigaDAOImpl implements BotigaDAO {
	
	public static int Tots(Connexio con, List<Botiga> botigues) {
		try {
			
			Statement stm = con.getConnexio().createStatement();
			String sql = "SELECT * FROM botiga";
			
			ResultSet rst = stm.executeQuery(sql);

			while(rst.next())
			{
				botigues.add(new Botiga(rst.getInt("idBotiga"), rst.getString("telefon"), rst.getString("direccio"), rst.getString("descripcio")));
			}
			
			return botigues.size();
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int create(Connexio con, Botiga botiga) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("INSERT INTO botiga VALUES (?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, botiga.getIdBotiga());
			stm.setString(2, botiga.getTelefon());
			stm.setString(3, botiga.getDireccio());
			stm.setString(4, botiga.getDescripcio());

			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Connexio con, Botiga botiga) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("UPDATE botiga SET telefon = ?, direccio = ?, descripcio = ? WHERE idBotiga = ? ");
			stm.setInt(1, botiga.getIdBotiga());
			stm.setString(2, botiga.getTelefon());
			stm.setString(3, botiga.getDireccio());
			stm.setString(4, botiga.getDescripcio());
			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int delete(Connexio con, int id) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement stm = con.getConnexio().prepareStatement("DELETE FROM botiga WHERE idBotiga = ? ");
			stm.setInt(1, id);

			return stm.executeUpdate();
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
