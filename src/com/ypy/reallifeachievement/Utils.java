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
	public static String str2md5(String input) {
		String md5 = null;

		if(null == input) return null;

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static String serialize(ACGroup object)
	{
		Gson gson = new Gson();

		return gson.toJson(object);
	}

	public static ACGroup deserialize(String string)
	{
		Gson gson = new Gson();

		return gson.fromJson(string, ACGroup.class);
	}

	public static ArrayList<ACGroup> RestoreAllGroup(Context ctxt) {
		ArrayList<ACGroup> myGroups = new ArrayList<ACGroup>();
		String[] files = ctxt.fileList();
		String thisjson = null;

		for (String filename : files) {
			Log.i("DEBUGME","ALL FILES " + filename);
			if (filename.contains(ACGroup.EXT)) {
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
