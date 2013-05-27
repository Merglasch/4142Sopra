//Registriert einen Nutzer in der Datenbank
//braucht: String email, String name,String vorname, String passwort, int rolle, String fakultaet
//Typ: void
package model.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ConnectFunctions;


public class DBMethoden {
	
	public void createUser(String email, String name,String vorname, String passwort, int rolle, String fakultaet){
		ConnectFunctions.createConnection();
		try {
			PreparedStatement stmt = ConnectFunctions.con.prepareStatement("INSERT INTO Users (uID, email,name,vorname,passwort,rolle,fakultaet) VALUES ((SELECT MAX(uID) +1 FROM Users),?,?,?,?,?,?)");
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
	
	public void updateUser(int uID, String email, String name,String vorname, String passwort, int rolle, String fakultaet) {
		ConnectFunctions.createConnection();
		try {
			PreparedStatement stmt = ConnectFunctions.con.prepareStatement("UPDATE Users SET email=?, name=?, vorname=?, passwort=?, rolle=?, fakultaet=? WHERE uID =?");
			stmt.setString(1, email);
			stmt.setString(2, name);
			stmt.setString(3, vorname);
			stmt.setString(4, passwort);
			stmt.setInt(5, rolle);
			stmt.setString(6, fakultaet);
			stmt.setInt(7, uID);
			stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		ConnectFunctions.shutdown();
	}
	
	public void deleteUser (int uID) {
		ConnectFunctions.createConnection();
		try {
			PreparedStatement stmt = ConnectFunctions.con.prepareStatement("DELETE FROM Users WHERE uID=?");
			stmt.setInt(1, uID);
			stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static int login(String email, String pw) {
		ConnectFunctions.createConnection();
		
		String name="";
		String vorName="";
		String eMail="";
		int rolle=-1;
		klassenDB.User sessionUser;


		try {
			PreparedStatement stmt = ConnectFunctions.con.prepareStatement("SELECT * FROM Users WHERE email = ? AND passwort = ?");
			stmt.setString(1, email);
			stmt.setString(2, pw);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				rolle = rs.getInt("rolle");
				name = rs.getString("name");
				vorName = rs.getString("vorname");
				eMail = rs.getString("email");
			}
			if(rolle!=-1){
				sessionUser= new klassenDB.User(vorName, name, eMail, rolle);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		ConnectFunctions.shutdown();
		return rolle;
	}
	
}
