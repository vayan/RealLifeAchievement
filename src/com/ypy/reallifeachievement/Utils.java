package com.ypy.reallifeachievement;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	public String str2md5(String str) {
		MessageDigest msgdig = null;
		byte[] bytest = null;
		
		try {
			msgdig = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			bytest = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] hashbyte = msgdig.digest(bytest);
		return hashbyte.toString();
	}	
}
