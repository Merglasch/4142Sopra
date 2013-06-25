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
import java.util.LinkedList;
import java.util.List;

import klassenDB.Benachrichtigung;
import klassenDB.Modul;
import klassenDB.User;
import model.ConnectFunctions;


public class DBMethoden {
	///////////////
	//////// Registrieren
	//////////////
	public static void createUser(String email, String name,String vorname, String passwort, int rolle, String fakultaet){
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
	
	public static  void updateUser(int uID, String email, String name,String vorname, String passwort, int rolle, String fakultaet) {
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
	
	public static void deleteUser (int uID) {
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
	public static void deleteUser(User u){
		int userID = u.getUid();
		deleteUser(userID);
	}
	public static void deleteUser(List<User> u){
		for(User user : u )
		deleteUser(user);
	}
	
	//////////////
	///////// Login
	///////////////	
	public static klassenDB.User login(String email, String pw) {
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
//		boolean freigegeben = m.isFreigegeben();
		Short freigegeben = m.getFreigegeben();
		 
//		 klassenDB.User sessionUser;

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
				"\","+ dezernat + "," +uID + ",\""+ freigegeben + "\")";
  
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

	public static void modulSpeichern(List<Modul> m){
		for(Modul modul : m){
			//modulSpeichern(modul);
		}
	}
	
	
	
	///////////////////////
	///////// Modul aus DB Laden
	/////////////////////////
	
	/*public Modul loadModul(int modulID){
		ConnectFunctions.createConnection();
		Modul m =null;
		try{
			Statement stmt = ConnectFunctions.con.createStatement();
			String query = "SELECT * FROM Modul WHERE modulID ="+modulID;
			stmt.execute(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				m = new Modul();
				m.setModulid(modulID);
				
				String modulname=rs.getString("modulname");
				m.setModulname(modulname);
				
				String code=rs.getString("code");
				m.setCode(code);
				
				String englisch=rs.getString("englisch");
				m.setEnglisch(englisch);
				
				String leistungspunkte=rs.getString("leistungspunkte");
				m.setLeistungspunkte(leistungspunkte);
				
				Short wochenstunden=rs.getShort("wochenstunden");
				m.setWochenstunden(wochenstunden);
				
				String sprache=rs.getString("sprache");
				m.setSprache(sprache);
				
				Short dauer=rs.getShort("dauer");
				m.setDauer(dauer);
				
				String turnus=rs.getString("turnus");
				m.setTurnus(turnus);
				
				String modulverantwortlicher=rs.getString("modulverandwortlicher");
				m.setModulverantwortlicher(modulverantwortlicher);
				
				String dozenten=rs.getString("dozenten");
				m.setDozenten(dozenten);
				
				String einordnung=rs.getString("einordnung");
				m.setEinordnung(einordnung);
				
				String voraussetzungenIn=rs.getString("voraussetzungenIn");
				m.setVoraussetzungenin(voraussetzungenIn);
				
				String lernziele=rs.getString("lernziele");
				m.setLernziele(lernziele);
				
				String inhalt=rs.getString("inhalt");
				m.setInhalt(inhalt);
				
				String literatur=rs.getString("literatur");
				m.setLiteratur(literatur);
				
				String grundlagefuer=rs.getString("grundlagefuer");
				m.setGrundlagefuer(grundlagefuer);
				
				String lehrformen=rs.getString("lehrformen");
				m.setLehrformen(lehrformen);
				
				String arbeitsaufwand=rs.getString("arbeitsaufwand");
				m.setArbeitsaufwand(arbeitsaufwand);
				
				String leistungsnachweis=rs.getString("leistungsnachweis");
				m.setLeistungsnachweis(leistungsnachweis);
				
				String voraussetzungenFor=rs.getString("voraussetzungFor");
				m.setVoraussetzungenfor(voraussetzungenFor);
				
				String notenbildung=rs.getString("notenbildung");
				m.setNotenbildung(notenbildung);
				
				Date stichtag=rs.getDate("stichtag");
				m.setStichtag(stichtag);
				
				Timestamp zeitstempel=rs.getTimestamp("zeitstempel");
				m.setZeitstempel(zeitstempel);
				
				Short dezernat=rs.getShort("dezernat");
				m.setDezernat(dezernat);
				
				int uID=rs.getInt("uID"); // references users
				m.setUid(uID);
				
				Short freigegeben = rs.getShort("freigegeben");
				m.setFreigegeben(freigegeben);
				
//				m = new Modul(modulID, modulname, code, englisch, leistungspunkte, 
//						wochenstunden, sprache, dauer, turnus, modulverandwortlicher, 
//						dozenten, einordnung, voraussetzungenIn, lernziehle, inhalt, literatur,
//						grundlagefuer, lehrformen, arbeitsaufwand, leistungsnachweis, voraussetzungenFor, 
//						notenbildung, stichtag, zeitstempel, dezernat, uID, freigegeben);
			}
			
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		ConnectFunctions.shutdown();
		return m;
	}*/
	
	public static List<Modul> loadModulAll(){
		ConnectFunctions.createConnection();
		List<Modul> module = new LinkedList<Modul>();
		
		Modul m =null;
		try{
			Statement stmt = ConnectFunctions.con.createStatement();
			String query="SELECT * FROM Modul";
			stmt.execute(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				m = new Modul();
				
				int modulID = rs.getInt("ModulID");
				m.setModulid(modulID);
				
				String modulname=rs.getString("modulname");
				m.setModulname(modulname);
				
				String code=rs.getString("code");
				m.setCode(code);
				
				String englisch=rs.getString("englisch");
				m.setEnglisch(englisch);
				
				String leistungspunkte=rs.getString("leistungspunkte");
				m.setLeistungspunkte(leistungspunkte);
				
				Short wochenstunden=rs.getShort("wochenstunden");
				m.setWochenstunden(wochenstunden);
				
				String sprache=rs.getString("sprache");
				m.setSprache(sprache);
				
				Short dauer=rs.getShort("dauer");
				m.setDauer(dauer);
				
				String turnus=rs.getString("turnus");
				m.setTurnus(turnus);
				
				String modulverantwortlicher=rs.getString("modulverandwortlicher");
				m.setModulverantwortlicher(modulverantwortlicher);
				
				String dozenten=rs.getString("dozenten");
				m.setDozenten(dozenten);
				
				String einordnung=rs.getString("einordnung");
				m.setEinordnung(einordnung);
				
				String voraussetzungenIn=rs.getString("voraussetzungenIn");
				m.setVoraussetzungenin(voraussetzungenIn);
				
				String lernziele=rs.getString("lernziele");
				m.setLernziele(lernziele);
				
				String inhalt=rs.getString("inhalt");
				m.setInhalt(inhalt);
				
				String literatur=rs.getString("literatur");
				m.setLiteratur(literatur);
				
				String grundlagefuer=rs.getString("grundlagefuer");
				m.setGrundlagefuer(grundlagefuer);
				
				String lehrformen=rs.getString("lehrformen");
				m.setLehrformen(lehrformen);
				
				String arbeitsaufwand=rs.getString("arbeitsaufwand");
				m.setArbeitsaufwand(arbeitsaufwand);
				
				String leistungsnachweis=rs.getString("leistungsnachweis");
				m.setLeistungsnachweis(leistungsnachweis);
				
				String voraussetzungenFor=rs.getString("voraussetzungFor");
				m.setVoraussetzungenfor(voraussetzungenFor);
				
				String notenbildung=rs.getString("notenbildung");
				m.setNotenbildung(notenbildung);
				
				Date stichtag=rs.getDate("stichtag");
				m.setStichtag(stichtag);
				
				Timestamp zeitstempel=rs.getTimestamp("zeitstempel");
				m.setZeitstempel(zeitstempel);
				
				Short dezernat=rs.getShort("dezernat");
				m.setDezernat(dezernat);
				
				int uID=rs.getInt("uID"); // references users
				m.setUid(uID);
				
				Short freigegeben = rs.getShort("freigegeben");
				m.setFreigegeben(freigegeben);
				
				module.add(m); // modul m zur Modulliste hinzufuegen
			}
			
			rs.close();
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		ConnectFunctions.shutdown();
		return module;
		
	}
	////////////////////////////////
	//////////// Modul loeschen
	////////////////////////////////
	
	public static void deleteModul(int modulID){
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
	public static void deleteModul(Modul m){
		deleteModul(m.getModulid());
	}
	public static void deleteModul(List<Modul> m){
		for(Modul modul : m){
			deleteModul(modul.getModulid());
		}
	}
	
	////////////////////////////////
	//////////// Benachrichtigung loeschen
	////////////////////////////////

	public static void deleteBenachrichtigung(int nachrichtID){
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
	public static  void deleteBenachrichtigung(Benachrichtigung b){
		deleteBenachrichtigung(b.getNachrichtid());
	}
	public static void deleteBenachrichtigung(List<Benachrichtigung> b){
		for(Benachrichtigung benachrichtigung : b){
			deleteBenachrichtigung(benachrichtigung.getNachrichtid());
		}
	}
	
	
}
