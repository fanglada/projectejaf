package dam2.jaf;

import java.sql.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Connexio {
	
	private Connection connexio;

	public Connexio(String host, String port, String bd, String user, String passwd) {

		try {
			this.connexio = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+bd,user,passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connexio() {
		try {
			this.connexio = DriverManager.getConnection("jdbc:mysql://localhost:3306/practica","fanglada","Dam2020!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connexio(String arxiu) {
		try {
			
			File file = new File(arxiu);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(file);  
			doc.getDocumentElement().normalize();
			 
			Element eElement = (Element) doc.getElementsByTagName(doc.getDocumentElement().getNodeName()).item(0);  

			String host = eElement.getElementsByTagName("host").item(0).getTextContent();
			String port = eElement.getElementsByTagName("port").item(0).getTextContent();
			String bd = eElement.getElementsByTagName("bd").item(0).getTextContent();
			String user = eElement.getElementsByTagName("user").item(0).getTextContent();
			String passwd = eElement.getElementsByTagName("passwd").item(0).getTextContent();
			
			this.connexio = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+bd,user,passwd);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnexio() {
		return connexio;
	}
	
}
