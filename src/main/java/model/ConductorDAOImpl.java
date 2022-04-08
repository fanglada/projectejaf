package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import dam2.jaf.Connexio;

	public class ConductorDAOImpl implements ConductorDAO {
	
		public static int Tots(Connexio con, List<Conductor> conductors) {
			
			try 
			{
				Connection conection = con.getConnexio();
				
				Statement stm = conection.createStatement();
				
				String sql = "SELECT * FROM treballador WHERE esConductor IS TRUE AND idBotiga IS NULL AND telefonEmpresa IS NULL;";
				
				ResultSet rst = stm.executeQuery(sql);		
				
				while(rst.next()){
					
					conductors.add(new Conductor(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail"),CarnetDAOImpl.BuscarConductor(con, rst.getString("DNI"))));
				}

				return conductors.size();
			}catch (Exception e) 
			{
				e.printStackTrace();
				return 0;
			}	
		}
		
		public static int Disponible(Connexio con, List<Conductor> conductors) {
			
			try 
			{
				Connection conection = con.getConnexio();
				
				Statement stm = conection.createStatement();
				
				String sql = "SELECT * FROM treballador WHERE esConductor IS TRUE AND idBotiga IS NULL AND telefonEmpresa IS NULL AND ( dni NOT IN (SELECT DNIConductor FROM contracte WHERE dataFi > NOW()) AND DNI!='0');";
				
				ResultSet rst = stm.executeQuery(sql);		
				
				while(rst.next()){
					
					conductors.add(new Conductor(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail"),CarnetDAOImpl.BuscarConductor(con, rst.getString("DNI"))));
				}

				return conductors.size();
			}catch (Exception e) 
			{
				e.printStackTrace();
				return 0;
			}	
		}
		
		public static int select(Connexio con, Conductor conductor, String id) {
			try 
			{
				Connection conection = con.getConnexio();
				
				Statement stm = conection.createStatement();
				
				String sql = "SELECT * FROM treballador WHERE DNI='"+id+"';";
				
				ResultSet rst = stm.executeQuery(sql);		
				
				if(rst.next()){
					
					conductor = new Conductor(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail"),CarnetDAOImpl.BuscarConductor(con, rst.getString("DNI")));
				}

				return 1;
			}catch (Exception e) 
			{
				e.printStackTrace();
				return 0;
			}	
		}	
		
		
		public static Conductor select(Connexio con, String id) {
			
			Conductor conductor = null;
			try 
			{
				Connection conection = con.getConnexio();
				
				Statement stm = conection.createStatement();
				
				String sql = "SELECT * FROM treballador WHERE DNI='"+id+"';";
				
				ResultSet rst = stm.executeQuery(sql);		
				
				if(rst.next()){
					
					conductor = new Conductor(rst.getString("DNI"), rst.getString("nom"), rst.getString("cognom1"), rst.getString("cognom2"), rst.getDate("dataNaixement").toLocalDate(), rst.getString("telefon"), rst.getString("direccio"), rst.getString("mail"),CarnetDAOImpl.BuscarConductor(con, rst.getString("DNI")));
				}

				return conductor;
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}		
		}

		@Override
		public int create(Connexio con, Conductor conductor) {
			
			try 
			{
				if(ConductorDAOImpl.select(con, conductor.getDni())!= null)
				{
					Connection conection = con.getConnexio();
					PreparedStatement stm = conection.prepareStatement("INSERT INTO treballador VALUES (?,?,?,?,?,?,?,?,1,NULL,NULL)");
					stm.setString(1, conductor.getDni());
					stm.setString(2, conductor.getNom());
					stm.setString(3, conductor.getCognom1());
					stm.setString(4, conductor.getCognom2());
					stm.setDate(5, Date.valueOf(conductor.getDataNaixament()));
					stm.setString(6, conductor.getTelefon());
					stm.setString(7, conductor.getDireccio());
					stm.setString(8, conductor.getMail());
	
					int aux = stm.executeUpdate();
					int aux2 = 1;
					
					for(int i = 0; i < conductor.getCarnet().size(); i++) 
					{
						stm = conection.prepareStatement("INSERT INTO conductorCarnet VALUES (?,?)");
						stm.setInt(1, conductor.getCarnet().get(i).getIdCarnet());
						stm.setString(2, conductor.getDni());
						if (stm.executeUpdate() <= 0) 
						{
							aux2 = 0;
						}
					}	
	
					if (aux > 0 && aux2 > 0) 
					{
						return 1;
					}else 
					{
						return 0;
					}			
				}
				return 0;

			
			}catch (Exception e) 
			{
				e.printStackTrace();
				return 0;
			}
		}

		@Override
		public int update(Connexio con, Conductor conductor) {
			try 
			{
				Connection conection = con.getConnexio();
				PreparedStatement stm = conection.prepareStatement("UPDATE treballador SET nom=?,cognom1=?,cognom2=?,dataNaixement=?,telefon=?,direccio=?,mail=? WHERE DNI=?");
				stm.setString(1, conductor.getNom());
				stm.setString(2, conductor.getCognom1());
				stm.setString(3, conductor.getCognom2());
				stm.setDate(4, Date.valueOf(conductor.getDataNaixament()));
				stm.setString(5, conductor.getTelefon());
				stm.setString(6, conductor.getDireccio());
				stm.setString(7, conductor.getMail());
				stm.setString(8, conductor.getDni());
				int aux = stm.executeUpdate();
				int aux2 = 1, aux3 = 1;
				
				stm = conection.prepareStatement("DELETE FROM conductorCarnet WHERE DNIConductor=?");
				stm.setString(1, conductor.getDni());
				aux2 = stm.executeUpdate();
				
				for(int i = 0; i < conductor.getCarnet().size(); i++) 
				{
					stm = conection.prepareStatement("INSERT INTO conductorCarnet VALUES (?,?)");
					stm.setInt(1, conductor.getCarnet().get(i).getIdCarnet());
					stm.setString(2, conductor.getDni());
					if (stm.executeUpdate() <= 0) 
					{
						aux3 = 0;
					}
				}	

				if (aux > 0 && aux2 > 0 && aux3 > 0) 
				{
					return 1;
				}else 
				{
					return 0;
				}			

			
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
							
				PreparedStatement stm = conection.prepareStatement("DELETE FROM conductorCarnet WHERE DNIConductor=?");
				stm.setString(1, id);
				int aux = stm.executeUpdate(), aux2 = 1;
							
				stm = conection.prepareStatement("DELETE FROM treballador WHERE DNI=?");	
				
				stm.setString(1, id);
				
				aux2 = stm.executeUpdate();
				
				if (aux > 0 && aux2 > 0) 
				{
					return 1;
				}else 
				{
					return 0;
				}

			}catch (Exception e) 
			{
				e.printStackTrace();
				return 0;
			}
		}


}
