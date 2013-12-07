package com.ypy.reallifeachievement;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;

import com.google.gson.Gson;

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
	
	public String serialize(ACGroup object)
	{
		Gson gson = new Gson();
		
		return gson.toJson(object);
	}
	
	public ACGroup deserialize(String string)
	{
		Gson gson = new Gson();
		
		return gson.fromJson(string, ACGroup.class);
	}
	
	public void RestoreAllGroup() {
		//TODO : GET ALL FILES
	}
	
}
