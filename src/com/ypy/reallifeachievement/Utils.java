package com.ypy.reallifeachievement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

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
	
	public ArrayList<ACGroup> RestoreAllGroup(Context ctxt) {
		ArrayList<ACGroup> myGroups = new ArrayList<ACGroup>();
		String[] files = ctxt.fileList();
		String thisjson = null;
		
		for (String filename : files) {
			Log.i("DEBUGME","ALL FILES " + filename);
			if (filename.contains(".lazy.db")) {
				InputStream fos = null;
				Log.i("DEBUGME","FIND FILES Restoring");
				try {
					fos = ctxt.openFileInput(filename);
				} catch (FileNotFoundException e) {e.printStackTrace();}
				try {
					int ch;
					 StringBuffer strContent = new StringBuffer("");
				     
					 while( (ch = fos.read()) != -1)
				        strContent.append((char)ch);
					thisjson = strContent.toString();
					fos.close();
				} catch (IOException e) {e.printStackTrace();}
				myGroups.add(deserialize(thisjson.toString()));
			}
		}
		return myGroups;
	}
	
}
