package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import dam2.jaf.Connexio;

public class SupervisorDAOImpl implements SupervisorDAO {
	
	public static int Tots(Connexio con, List<Supervisor> supervisors) {
		try 
		{
			Connection conection = con.getConnexio();
			
			Statement stm = conection.createStatement();
			
			String sql = "SELECT * FROM treballador WHERE esConductor IS FALSE AND idBotiga IS NOT NULL AND telefonEmpresa IS NOT NULL;";
			
			ResultSet rst = stm.executeQuery(sql);		
			
			while(rst.next()){
				supervisors.add(new Supervisor(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail"), rst.getString("telefonEmpresa"), BotigaDAOImpl.select(con, rst.getInt("idBotiga"))));
			}

			return supervisors.size();
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int create(Connexio con, Supervisor supervisor) {
		try 
		{
			
			if(EmpleatDAOImpl.select(con, supervisor.getDni())!= null)
			{
				Connection conection = con.getConnexio();
				PreparedStatement stm = conection.prepareStatement("INSERT INTO treballador VALUES (?,?,?,?,?,?,?,?,0,?,?)");
				stm.setString(1, supervisor.getDni());
				stm.setString(2, supervisor.getNom());
				stm.setString(3, supervisor.getCognom1());
				stm.setString(4, supervisor.getCognom2());
				stm.setDate(5, Date.valueOf(supervisor.getDataNaixament()));
				stm.setString(6, supervisor.getTelefon());
				stm.setString(7, supervisor.getDireccio());
				stm.setString(8, supervisor.getMail());
				stm.setInt(9, supervisor.getBotiga().getIdBotiga());
				stm.setString(10, supervisor.getTelefonEmpresa());
				return stm.executeUpdate();			
			}
			return 0;
		
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Connexio con, Supervisor supervisor) {
		try 
		{
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement("UPDATE treballador SET nom=?,cognom1=?,cognom2=?,dataNaixement=?,telefon=?,direccio=?,mail=?,telefonEmpresa=?,idBotiga=? WHERE DNI=?");
			stm.setString(1, supervisor.getNom());
			stm.setString(2, supervisor.getCognom1());
			stm.setString(3, supervisor.getCognom2());
			stm.setDate(4, Date.valueOf(supervisor.getDataNaixament()));
			stm.setString(5, supervisor.getTelefon());
			stm.setString(6, supervisor.getDireccio());
			stm.setString(7, supervisor.getMail());
			stm.setString(8, supervisor.getTelefonEmpresa());
			stm.setInt(9, supervisor.getBotiga().getIdBotiga());
			stm.setString(10, supervisor.getDni());
			return stm.executeUpdate();			

		
		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Connexio con, String id) {
		try 
		{
			Connection conection = con.getConnexio();
						
			PreparedStatement stm = conection.prepareStatement("DELETE FROM treballador WHERE DNI=?");	
			
			stm.setString(1, id);
			
			return stm.executeUpdate();

		}catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}

}
