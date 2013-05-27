package model;

import java.security.NoSuchAlgorithmException;

import model.account.DBMethoden;
import model.account.Kodierer;

public class main {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(DBMethoden.login("hans@dampf.de", "seppal"));
		System.out.println(new Kodierer().code("Hallo"));
		//registert einen user
		//Register.registerthis("mein@mail.de","Herrman","Dieter","mieter",1,"Elektrotechnik");
	}

}
