package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import dam2.jaf.Connexio;

public class ContracteDAOImpl implements ContracteDAO {
	
	public static int Tots(Connexio con, List<Contracte> contractes) {
		try {

			String sql = "SELECT * FROM contracte NATURAL JOIN estat;";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				
				if(resultSet.getString("DNIConductor") == null || resultSet.getString("DNIConductor").isEmpty()) 
				{
					contractes.add(new Contracte(resultSet.getInt("idContracte"), ClientDAOImpl.select(con, resultSet.getString("DNIClient")), EmpleatDAOImpl.select(con, resultSet.getString("DNI")), resultSet.getDate("dataInici").toLocalDate(), resultSet.getDate("dataFi").toLocalDate(),new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")), VehicleDAOImpl.select(con, resultSet.getString("matricula"))));
				}else 
				{
					contractes.add(new Contracte(resultSet.getInt("idContracte"), ClientDAOImpl.select(con, resultSet.getString("DNIClient")), EmpleatDAOImpl.select(con, resultSet.getString("DNI")), ConductorDAOImpl.select(con, resultSet.getString("DNIConductor")) , resultSet.getDate("dataInici").toLocalDate(), resultSet.getDate("dataFi").toLocalDate(),new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")), VehicleDAOImpl.select(con, resultSet.getString("matricula"))));
				}
				
			}
			return contractes.size();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int create(Connexio con, Contracte contracte) {
		try 
		{
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement("INSERT INTO contracte VALUES (NULL,?,?,?,?,?,?,?)");
			stm.setString(1, contracte.getVehicle().getMatricula());
			stm.setDate(2, Date.valueOf(contracte.getDataInici()));
			stm.setDate(3, Date.valueOf(contracte.getDataFi()));
			stm.setInt(4, contracte.getEstat().getIdEstat());
			stm.setString(5, contracte.getClient().getDni());
			stm.setString(6, contracte.getEmpleat().getDni());
			if (contracte.getConductor() != null) 
			{
				stm.setString(7, contracte.getConductor().getDni());
			}else 
			{
				stm.setNull(7, Types.VARCHAR);
			}
			
			return stm.executeUpdate();			

		
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Connexio con, Contracte contracte) {
		try 
		{
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement("UPDATE contracte SET matricula=?,dataInici=?,dataFi=?,idEstat=?,DNIClient=?,DNITreballador=?,DNIConductor=? WHERE idContracte=?");
			stm.setString(7, contracte.getVehicle().getMatricula());
			stm.setDate(1, Date.valueOf(contracte.getDataInici()));
			stm.setDate(2, Date.valueOf(contracte.getDataFi()));
			stm.setInt(3, contracte.getEstat().getIdEstat());
			stm.setString(4, contracte.getClient().getDni());
			stm.setString(5, contracte.getEmpleat().getDni());
			if (contracte.getConductor() != null) 
			{
				stm.setString(6, contracte.getConductor().getDni());
			}else 
			{
				stm.setNull(6, Types.VARCHAR);
			}
			return stm.executeUpdate();			

		
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Connexio con, int id) {
		try 
		{
			Connection conection = con.getConnexio();
						
			PreparedStatement stm = conection.prepareStatement("DELETE FROM contracte WHERE idContracte=?");	
			
			stm.setInt(1, id);
			
			return stm.executeUpdate();

		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

}
