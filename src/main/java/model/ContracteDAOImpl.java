package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import dam2.jaf.Connexio;

public class ContracteDAOImpl implements ContracteDAO {
	
	public static int Tots(Connexio con, List<Contracte> contractes) {
		try {

			String sql = "SELECT * FROM contracte NATURAL JOIN estat;";
			Statement stm = con.getConnexio().createStatement();

			ResultSet resultSet= stm.executeQuery(sql);

			while(resultSet.next()) {
				
				if(resultSet.getString("DNIConductor").equalsIgnoreCase("0")) 
				{
					contractes.add(new Contracte(resultSet.getInt("idContracte"), ClientDAOImpl.select(con, resultSet.getString("DNIClient")), EmpleatDAOImpl.select(con, resultSet.getString("DNITreballador")), resultSet.getDate("dataInici").toLocalDate(), resultSet.getDate("dataFi").toLocalDate(),new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")), VehicleDAOImpl.select(con, resultSet.getString("matricula"))));
				}else 
				{
					contractes.add(new Contracte(resultSet.getInt("idContracte"), ClientDAOImpl.select(con, resultSet.getString("DNIClient")), EmpleatDAOImpl.select(con, resultSet.getString("DNITreballador")), ConductorDAOImpl.select(con, resultSet.getString("DNIConductor")) , resultSet.getDate("dataInici").toLocalDate(), resultSet.getDate("dataFi").toLocalDate(),new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")), VehicleDAOImpl.select(con, resultSet.getString("matricula"))));
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
				stm.setString(7, "0");
			}
			
			stm.executeUpdate();
			
			String consultId = "SELECT @@Identity";
			ResultSet rst = stm.executeQuery(consultId);
			if (rst.next()) 
			{
				contracte.setIdContracte(rst.getInt(1));
			}
			
			PreparedStatement stm2 = con.getConnexio().prepareStatement("INSERT INTO movimentParking VALUES (NULL,?,?,?,?)");				
			stm2.setInt(1, contracte.getVehicle().getParking().getIdParking());
			stm2.setInt(2, 0);
			stm2.setString(3, contracte.getVehicle().getMatricula());
			stm2.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));


			return stm2.executeUpdate();			

		
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
			stm.setInt(8, contracte.getIdContracte());
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
