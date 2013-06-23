//Registriert einen Nutzer in der Datenbank
//braucht: String email, String name,String vorname, String passwort, int rolle, String fakultaet
//Typ: void
package model.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import klassenDB.Benachrichtigung;
import klassenDB.Modul;
import klassenDB.User;
import model.ConnectFunctions;


public class DBMethoden {
	///////////////
	//////// Registrieren
	//////////////
	public void createUser(String email, String name,String vorname, String passwort, int rolle, String fakultaet){
		ConnectFunctions db = ConnectFunctions.getDB();
		try {
			PreparedStatement stmt = db.con.prepareStatement("INSERT INTO Users (uID, email,name,vorname,passwort,rolle,fakultaet) VALUES ((SELECT MAX(uID) +1 FROM Users),?,?,?,?,?,?)");
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
		db.shutdown();
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
	public void deleteUser(User u){
		int userID = u.getUid();
		deleteUser(userID);
	}
	public void deleteUser(List<User> u){
		for(User user : u )
		deleteUser(user);
	}
	
	//////////////
	///////// Login
	///////////////	
	public klassenDB.User login(String email, String pw) {
		ConnectFunctions db = ConnectFunctions.getDB();
		
		int uID = -1;
		String name="";
		String Vorname="";
		String eMail="";
		int rolle=-1;
		String fakultaet="";
		klassenDB.User sessionUser = null;
		String passwort=null;


		try {
			PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM Users WHERE email = ? AND passwort = ?");
			stmt.setString(1, email);
			stmt.setString(2, pw);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				uID = rs.getInt("uID");
				passwort = rs.getString("passwort");
				eMail = rs.getString("email");
				name = rs.getString("name");
				Vorname = rs.getString("vorname");
				rolle = rs.getInt("rolle");
				fakultaet = rs.getString("fakultaet");
			}
			if(rolle!=-1){
				sessionUser= new klassenDB.User(uID, eMail, fakultaet, name, passwort, rolle, Vorname );
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		db.shutdown();
		if(sessionUser==null)
			sessionUser= new klassenDB.User(111, eMail, fakultaet, name, passwort, 1, Vorname );		
		return sessionUser;
	}
	
	
	////////////////////
	//// Modul erstellen, in DB speichern
	////////////////////
	/*public boolean modulSpeichern(Modul m) {
		ConnectFunctions.createConnection();

		int modulID = m.getModulid(); // PK
		String modulname=m.getModulname();
		String code=m.getCode();
		String englisch=m.getEnglisch();
		String leistungspunkte=m.getLeistungspunkte();
		int wochenstunden=m.getWochenstunden();
		String sprache=m.getSprache();
		int dauer=m.getDauer();
		String turnus=m.getTurnus();
		String modulverandwortlicher=m.getModulverantwortlicher();
		String dozenten=m.getDozenten();
		String einordnung=m.getEinordnung();
		String voraussetzungenIn=m.getVoraussetzungenin();
		String lernziehle=m.getLernziele();
		String inhalt=m.getInhalt();
		String literatur=m.getLiteratur();
		String grundlagefuer=m.getGrundlagefuer();
		String lehrformen=m.getLehrformen();
		String arbeitsaufwand=m.getArbeitsaufwand();
		String leistungsnachweis=m.getArbeitsaufwand();
		String voraussetzungenFor=m.getVoraussetzungenfor();
		String notenbildung=m.getNotenbildung();
		Date stichtag=m.getStichtag();
		Timestamp zeitstempel=m.getZeitstempel();
		int dezernat=m.getDezernat();
		int uID=m.getUid(); // references users
		boolean freigegeben = m.isFreigegeben();
		 
		 klassenDB.User sessionUser;

		 String qInsert="INSERT INTO Modul (modulID, modulname, code, englisch, " +
		 		"leistungspunkte, wochenstunden,sprache, dauer,turnus,modulverandwortlicher,dozenten," +
		 		"einordnung, voraussetzungenIn, lernziehle, inhalt, literatur, grundlagefuer," +
		 		"lehrformen, arbeitsaufwand, leistungsnachweis, voraussetzungenFor," +
		 		"notenblidung, stichtag, zeitstempel, dezernat, uID, freigegeben)" +
		 		"VALUES (" ;
		 //modulID verwalten, modulID = -1 benoetigt neue modulID
		 if(modulID == -1){
			 qInsert += "(SELECT MAX(modulID)+1 FROM Modul),";
		 }else{
			 qInsert += ""+modulID + ",";
		 }
		// modulID schon bedient 		
		String qValues= "\""+ modulname +"\",\"" +code+"\", \""+ englisch+"\",\" "+leistungspunkte +
				" \",\""+ wochenstunden + ", \"" +sprache+"\","+ dauer+ ",\""+turnus+ "\",\""+
				modulverandwortlicher+"\",\""+dozenten+"\",\""+einordnung+"\",\""+ voraussetzungenIn+
				"\",\"" + lernziehle + "\",\"" +inhalt + "\",\""+ literatur+ "\",\""+ grundlagefuer+
				"\",\"" +lehrformen+"\",\"" + arbeitsaufwand +"\",\"" + leistungsnachweis + "\",\""+ 
				voraussetzungenFor + "\",\""+notenbildung+"\",\"" + stichtag +"\",\"" + zeitstempel +
				"\","+ dezernat + "," +uID + ", \""+ freigegeben + "\")";
  
		String query = qInsert +qValues;
		try {
			Statement stmt = ConnectFunctions.con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			ConnectFunctions.shutdown();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectFunctions.shutdown();
			return false;
		} 
	}*/


	
	
	
	///////////////////////
	///////// Modul aus DB Laden
	/////////////////////////
	
	/*public Modul loadModul(int modulID){
		ConnectFunctions.createConnection();
		Modul m =null;
		try{
			Statement stmt = ConnectFunctions.con.createStatement();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				String modulname=rs.getString("modulname");
				String code=rs.getString("code");
				String englisch=rs.getString("englisch");
				String leistungspunkte=rs.getString("leistungspunkte");
				int wochenstunden=rs.getInt("wochenstunden");
				String sprache=rs.getString("sprache");
				int dauer=rs.getInt("dauer");
				String turnus=rs.getString("turnus");
				String modulverandwortlicher=rs.getString("modulverandwortlicher");
				String dozenten=rs.getString("dozenten");
				String einordnung=rs.getString("einordnung");
				String voraussetzungenIn=rs.getString("voraussetzungenIn");
				String lernziehle=rs.getString("lernziehle");
				String inhalt=rs.getString("inhalt");
				String literatur=rs.getString("literatur");
				String grundlagefuer=rs.getString("grundlagefuer");
				String lehrformen=rs.getString("lehrformen");
				String arbeitsaufwand=rs.getString("arbeitsaufwand");
				String leistungsnachweis=rs.getString("leistungsnachweis");
				String voraussetzungenFor=rs.getString("voraussetzungFor");
				String notenbildung=rs.getString("notenbildung");
				Date stichtag=rs.getDate("stichtag");
				Timestamp zeitstempel=rs.getTimestamp("zeitstempel");
				int dezernat=rs.getInt("dezernat");
				int uID=rs.getInt("uID"); // references users
				boolean freigegeben = rs.getBoolean("freigegeben");
				
				m = new Modul(modulID, modulname, code, englisch, leistungspunkte, 
						wochenstunden, sprache, dauer, turnus, modulverandwortlicher, 
						dozenten, einordnung, voraussetzungenIn, lernziehle, inhalt, literatur,
						grundlagefuer, lehrformen, arbeitsaufwand, leistungsnachweis, voraussetzungenFor, 
						notenbildung, stichtag, zeitstempel, dezernat, uID, freigegeben);
			}
			
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		ConnectFunctions.shutdown();
		return m;
	}*/
	
	
	////////////////////////////////
	//////////// Modul loeschen
	////////////////////////////////
	
	public void deleteModul(int modulID){
		ConnectFunctions.createConnection();
		try {
			PreparedStatement stmt = ConnectFunctions.con.prepareStatement("DELETE FROM Modul WHERE modulID=?");
			stmt.setInt(1, modulID);
			stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteModul(Modul m){
		deleteModul(m.getModulid());
	}
	public void deleteModul(List<Modul> m){
		for(Modul modul : m){
			deleteModul(modul.getModulid());
		}
	}
	
	////////////////////////////////
	//////////// Benachrichtigung loeschen
	////////////////////////////////

	public void deleteBenachrichtigung(int nachrichtID){
		ConnectFunctions.createConnection();
		try {
			PreparedStatement stmt = ConnectFunctions.con.prepareStatement("DELETE FROM Benachrichtigung WHERE nachrichtlID=?");
			stmt.setInt(1, nachrichtID);
			stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void deleteBenachrichtigung(Benachrichtigung b){
		deleteBenachrichtigung(b.getNachrichtid());
	}
	public void deleteBenachrichtigung(List<Benachrichtigung> b){
		for(Benachrichtigung benachrichtigung : b){
			deleteBenachrichtigung(benachrichtigung.getNachrichtid());
		}
	}
	
	
}
