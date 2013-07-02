package model;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import klassenDB.Modul;
//import model.account.DBMethoden;
import model.account.Kodierer;

public class main {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(new DBMethoden().login("hans@dampf.de", "seppal"));
		System.out.println(new Kodierer().code(""));
		//registert einen user
		//Register.registerthis("mein@mail.de","Herrman","Dieter","mieter",1,"Elektrotechnik");
		
	}

}
