package com.rhcloud.pugmg.cinetheatrosys.controle.uteis;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashs {
	public static String getSHA256(String original) {
		if(original==null) original="";
		MessageDigest algorithm;
		String senha="??";
		try {
			algorithm = MessageDigest.getInstance("SHA-256");

			byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senha = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return senha;
	}
}
