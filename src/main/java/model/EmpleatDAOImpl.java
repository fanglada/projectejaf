package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import dam2.jaf.Connexio;

public class EmpleatDAOImpl implements EmpleatDAO {

	public static int Tots(Connexio con, List<Empleat> empleats) {
		try {
			Connection conection = con.getConnexio();

			Statement stm = conection.createStatement();

			String sql = "SELECT * FROM treballador WHERE esConductor IS FALSE AND idBotiga IS NOT NULL AND telefonEmpresa IS NULL;";

			ResultSet rst = stm.executeQuery(sql);

			while (rst.next()) {
				empleats.add(new Empleat(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"),
						rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"),
						rst.getString("direccio"), rst.getString("mail"), BotigaDAOImpl.select(con, rst.getInt("idBotiga"))));
			}
			
			

			return empleats.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int select(Connexio con, Empleat empleat, String id) {
		try {
			Connection conection = con.getConnexio();

			Statement stm = conection.createStatement();

			String sql = "SELECT * FROM treballador WHERE DNI = '"+id+"';";

			ResultSet rst = stm.executeQuery(sql);

			if (rst.next()) {
				
				if(rst.getString("telefonEmpresa") == null || rst.getString("telefonEmpresa").isEmpty()) 
				{
					empleat = new Empleat(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"),
							rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"),
							rst.getString("direccio"), rst.getString("mail"), BotigaDAOImpl.select(con, rst.getInt("idBotiga")));
				}
				else 
				{
					empleat = new Supervisor(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"),
							rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"),
							rst.getString("direccio"), rst.getString("mail"),rst.getString("telefonEmpresa"), BotigaDAOImpl.select(con, rst.getInt("idBotiga")));
				}
			}

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Empleat select(Connexio con, String id) {
		Empleat empleat = null;
		try {
			Connection conection = con.getConnexio();

			Statement stm = conection.createStatement();

			String sql = "SELECT * FROM treballador WHERE DNI = '"+id+"';";

			ResultSet rst = stm.executeQuery(sql);

			if (rst.next()) {
				
				if(rst.getString("telefonEmpresa") == null || rst.getString("telefonEmpresa").isEmpty()) 
				{
					empleat = new Empleat(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"),
							rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"),
							rst.getString("direccio"), rst.getString("mail"), BotigaDAOImpl.select(con, rst.getInt("idBotiga")));
				}else 
				{
					empleat = new Supervisor(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"),
							rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"),
							rst.getString("direccio"), rst.getString("mail"),rst.getString("telefonEmpresa"), BotigaDAOImpl.select(con, rst.getInt("idBotiga")));
				}
			}

			return empleat;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public int create(Connexio con, Empleat empleat) {
		try {
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement("INSERT INTO treballador VALUES (?,?,?,?,?,?,?,?,0,?,NULL)");
			stm.setString(1, empleat.getDni());
			stm.setString(2, empleat.getNom());
			stm.setString(3, empleat.getCognom1());
			stm.setString(4, empleat.getCognom2());
			stm.setDate(5, Date.valueOf(empleat.getDataNaixament()));
			stm.setString(6, empleat.getTelefon());
			stm.setString(7, empleat.getDireccio());
			stm.setString(8, empleat.getMail());
			stm.setInt(9, empleat.getBotiga().getIdBotiga());
		

			return stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Connexio con, Empleat empleat) {
		try {
			Connection conection = con.getConnexio();
			PreparedStatement stm = conection.prepareStatement(
					"UPDATE treballador SET nom=?,cognom1=?,cognom2=?,dataNaixement=?,telefon=?,direccio=?,mail=?,idBotiga=? WHERE DNI=?");
			stm.setString(1, empleat.getNom());
			stm.setString(2, empleat.getCognom1());
			stm.setString(3, empleat.getCognom2());
			stm.setDate(4, Date.valueOf(empleat.getDataNaixament()));
			stm.setString(5, empleat.getTelefon());
			stm.setString(6, empleat.getDireccio());
			stm.setString(7, empleat.getMail());
			stm.setInt(8, empleat.getBotiga().getIdBotiga());
			stm.setString(9, empleat.getDni());
			return stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Connexio con, String id) {
		try {
			Connection conection = con.getConnexio();

			PreparedStatement stm = conection.prepareStatement("DELETE FROM treballador WHERE DNI=?");

			stm.setString(1, id);

			return stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
