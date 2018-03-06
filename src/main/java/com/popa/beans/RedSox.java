package com.popa.beans;

public class RedSox extends Team{

	@Override
	public String getName() {
		return "Red Sox";
	}
	
	public void prepare() {
		System.out.println("Red Sox team is prepared. Ready to start");
	}
	
	public void goHome() {
		System.out.println("Red Sox team will go home now");
	}
}
