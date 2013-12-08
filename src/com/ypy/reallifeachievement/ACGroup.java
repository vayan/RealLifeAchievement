package com.ypy.reallifeachievement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class ACGroup {
	private String id; //md5 hash of the name
	private String id_state; //md5 changed at every update
	private String name;
	private String descr;
	private ArrayList<ACItem> acs;
	
	public static String EXT = ".lazy.db";

	public ACGroup(String name, String descr) {
		super();
		this.name = name;
		this.descr = descr;
		acs = new ArrayList<ACItem>();
		this.setId(name);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		String id_md5 = Utils.str2md5(id);
		this.id = id_md5;
	}
	public String getId_state() {
		return id_state;
	}
	public void setId_state(String id_state) {
		this.id_state = id_state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

	public ArrayList<ACItem> getAcs() {
		return acs;
	}
	public void setAcs(ArrayList<ACItem> acs) {
		this.acs = acs;
	}
	public void AddACItem(ACItem new_ac) {
		this.acs.add(new_ac);
		this.onChangeData();
		
	}
	
	public ACItem GetACItem() {
		
		return null;
	}

	public void RmACItem(ACItem ac) {
		this.acs.remove(ac);
		this.onChangeData();
	}
	public void EditACItem(ACItem new_ac) {
		for (ACItem ac : acs) {
			if (ac.getId() == new_ac.getId()) {
				this.RmACItem(ac); 
				this.AddACItem(new_ac); //TODO : useless 2 call onChangeData
				return;
			}
		}
	}
	
	public void onChangeData() {
		updatePoints();
		updateHashState();
	}

	public void updatePoints() {
		int base_pts = 5;
		for (ACItem ac : acs) {
			ac.setPoints(base_pts);
			base_pts *= base_pts;
		}
	}
	
	public void updateHashState() {
		String hash = name;
		for (ACItem ac : acs) {
			hash = Utils.str2md5(hash+ac.getId());
		}
		setId_state(hash);
	}

	public String getScore() {		
		int completed = 0;
		for (ACItem ac : acs) if (ac.getDone()) completed ++;
		return Integer.toString(completed) + "/" + Integer.toString(acs.size());
	}
	
	public void saveMe(Context ctxt) {
		//TOOD : Maybe check overwrite
		String filename = this.id+EXT;
		String thisjson = Utils.serialize(this);

		FileOutputStream fos = null;
		try {
			fos = ctxt.openFileOutput(filename, ctxt.MODE_PRIVATE);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		try {
			fos.write(thisjson.getBytes());
			fos.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void restoreMe(Context ctxt) {
		//TODO : Check if I don't exist
		String filename = this.id+EXT;
		String thisjson = null;
		
		FileInputStream fos = null;
		try {
			fos = ctxt.openFileInput(filename);
		} catch (FileNotFoundException e) {e.printStackTrace(); 
		Log.i("DEBUGME","FILE NOT FOUND BYBYE " + filename);
		return;}
		try {
			 int ch;
			 StringBuffer strContent = new StringBuffer("");
		     
			 while((ch = fos.read()) != -1)
		        strContent.append((char)ch);
			thisjson = strContent.toString();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace(); 
			Log.i("DEBUGME","IO BUG BYBYE " + filename); 
			return;
		}
		
		ACGroup bu = Utils.deserialize(thisjson);
		this.name = bu.getName();
		this.descr = bu.getDescr();
		
		for (ACItem ac : bu.getAcs()) {
			this.AddACItem(ac);
		}
	}
	
	public void deleteMe(Context ctxt) {
		String filename = this.id+EXT;
		ctxt.deleteFile(filename);
	}

	public void debugAfflist() {
		Log.i("DEBUGME","START DEBUG AFF LIST");
		for (ACItem ac : acs ) {
			Log.i("DEBUGME",ac.getName());
		}
	}



}
