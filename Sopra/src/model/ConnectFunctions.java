package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectFunctions {

	private static String dbURL = "jdbc:derby:H:\\.win7_profile\\Documents\\GitHub\\4142Sopra\\Sopra\\MyDB;create=true";
	public static Connection con = null;

	public static void createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			con = DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void shutdown() {
		try {
			if (con != null) {
				DriverManager.getConnection(dbURL + ";shutdown=true");
				con.close();
			}
		} catch (Exception e) {
			
		}
	}
}
