package com.ypy.reallifeachievement;

import android.util.Log;

public class ACItem {
	private int weight;
	private String id; //md5 hash of the name
	private String name;
	private String descr;
	private boolean done;
	private int points;
	
	public ACItem(String name, String descr) {
		super();
		this.name = name;
		this.descr = descr;
		this.done = false;
		this.weight = 0;
		this.points = 0;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
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
	public boolean getDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		String id_md5 = Utils.str2md5(id);
		this.id = id_md5;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}	
	
}	
