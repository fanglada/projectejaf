package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
//					contractes.add(new Contracte(resultSet.getInt("idContracte"), ClientDAOImpl.select(con, resultSet.getString("DNIClient")), new Empleat(resultSet.getString("DNI"), resultSet.getString("nom"), resultSet.getString("cognom1"), resultSet.getString("cognom2"), resultSet.getDate("dataNaixement").toLocalDate(), resultSet.getString("telefon"), resultSet.getString("direccio"), resultSet.getString("mail")), resultSet.getDate("dataInici").toLocalDate(), resultSet.getDate("dataFi").toLocalDate(),new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")), VehicleDAOImpl.select(con, resultSet.getString("matricula"))));
				}else 
				{
					contractes.add(new Contracte(resultSet.getInt("idContracte"), ClientDAOImpl.select(con, resultSet.getString("DNIClient")), EmpleatDAOImpl.select(con, resultSet.getString("DNI")), ConductorDAOImpl.select(con, resultSet.getString("DNIConductor")) , resultSet.getDate("dataInici").toLocalDate(), resultSet.getDate("dataFi").toLocalDate(),new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")), VehicleDAOImpl.select(con, resultSet.getString("matricula"))));
//					contractes.add(new Contracte(resultSet.getInt("idContracte"), ClientDAOImpl.select(con, resultSet.getString("DNIClient")), new Empleat(resultSet.getString("DNI"), resultSet.getString("nom"), resultSet.getString("cognom1"), resultSet.getString("cognom2"), resultSet.getDate("dataNaixement").toLocalDate(), resultSet.getString("telefon"), resultSet.getString("direccio"), resultSet.getString("mail")), ConductorDAOImpl.select(con, resultSet.getString("DNIConductor")) , resultSet.getDate("dataInici").toLocalDate(), resultSet.getDate("dataFi").toLocalDate(),new Estat(resultSet.getInt("idEstat"), resultSet.getString("descripcio")), VehicleDAOImpl.select(con, resultSet.getString("matricula"))));

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Connexio con, Contracte contracte) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Connexio con, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
