//Registriert einen Nutzer in der Datenbank
//braucht: String email, String name,String vorname, String passwort, int rolle, String fakultaet
//Typ: void
package model.account;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ConnectFunctions;


public class Register {
	public static void registerthis(String email, String name,String vorname, String passwort, int rolle, String fakultaet){
		ConnectFunctions.createConnection();
		try{
			PreparedStatement stmt = ConnectFunctions.con.prepareStatement("INSERT INTO Users (email,name,vorname,passwort,rolle,fakultaet) VALUES (?,?,?,?,?,?)");
			stmt.setString(1, email);
			stmt.setString(2, name);
			stmt.setString(3, vorname);
			stmt.setString(4, passwort);
			stmt.setInt(5, rolle);
			stmt.setString(6, fakultaet);
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		ConnectFunctions.shutdown();
	}
	
	
}
