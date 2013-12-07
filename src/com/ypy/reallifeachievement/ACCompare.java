package com.ypy.reallifeachievement;

public class ACCompare {
	private ACItem you;
	private ACItem other;
	
	public ACCompare(ACItem you, ACItem other) {
		this.you = you;
		this.other = other;
	}
	
	public ACItem getYou() {
		return you;
	}
	public void setYou(ACItem you) {
		this.you = you;
	}
	public ACItem getOther() {
		return other;
	}
	public void setOther(ACItem other) {
		this.other = other;
	}
}
