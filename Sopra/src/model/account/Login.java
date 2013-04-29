package model.account;

import java.sql.*;

import model.ConnectFunctions;

public class Login {
	public static int login(String email, String pw) {
		ConnectFunctions.createConnection();

		int Rolle = -1;

		try {
			PreparedStatement stmt = ConnectFunctions.con.prepareStatement("SELECT rolle FROM Users WHERE email = ? AND passwort = ?");
			stmt.setString(1, email);
			stmt.setString(2, pw);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				Rolle = rs.getInt(1);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		ConnectFunctions.shutdown();
		return Rolle;
	}
}
