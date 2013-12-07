package com.ypy.reallifeachievement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class ACGroup {
	private String id;
	private String name;
	private String descr;
	private List<ACItem> acs;
	
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
		String id_md5 = new Utils().str2md5(id);
		this.id = id_md5;
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
	
	public void AddACItem(ACItem ac) {
		this.acs.add(ac);
	}
	
	public void RmACItem(ACItem ac) {
		this.acs.remove(ac);
	}
	public void EditACItem(ACItem new_ac) {
		for (ACItem ac : acs) {
			if (ac.getId() == new_ac.getId()) {
				this.acs.remove(ac);
				this.acs.add(new_ac);
				return;
			}
		}
	}
	public void debugAfflist() {
		Log.i("DEBUGME","START DEBUG AFF LIST");
		for (ACItem ac : acs ) {
			Log.i("DEBUGME",ac.getName());
		}
	}
	
}
