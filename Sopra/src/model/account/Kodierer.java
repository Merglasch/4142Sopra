package model.account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Kodierer {

	public String code (String pw) {
		
		StringBuilder sb = null;
		try {
		//Codierung als SHA-1 in einem byte Array
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte[] digest = md.digest(pw.getBytes());
		
		//Byte Array zu Hexadezimalzahl und String umwandeln
		sb = new StringBuilder(digest.length * 2);
		Formatter formatter = new Formatter(sb);
		for (byte b : digest) {
			formatter.format("%02x", b);
		}
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}