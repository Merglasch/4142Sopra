package model.account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Kodierer {

	public String code (String pw) throws NoSuchAlgorithmException {
		
		//Codierung
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte[] digest = md.digest(pw.getBytes());
		
		//Byte Array zu Hexadezimal und String
		StringBuilder sb = new StringBuilder(digest.length * 2);
		Formatter formatter = new Formatter(sb);
		for (byte b : digest) {
			formatter.format("%02x", b);
		}
		return sb.toString();
	}
}