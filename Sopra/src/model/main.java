package model;

import java.security.NoSuchAlgorithmException;

import model.account.Login;
import model.account.Register;
import model.account.Kodierer;

public class main {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		System.out.println(Login.login("hans@dampf.de", "seppal"));
		System.out.println(new Kodierer().code("Hallo"));
		//registert einen user
		//Register.registerthis("mein@mail.de","Herrman","Dieter","mieter",1,"Elektrotechnik");
	}

}
