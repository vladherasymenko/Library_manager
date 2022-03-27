package com.ensta.librarymanager.utils;

public enum Abonnement {
	
	BASIC(2),
	PREMIUM(5),
	VIP(20);
	
	private int value;
	
	Abonnement(int i) {
		this.value = i;
	}

	public int getValue() {
		return this.value;
	}
	
	
}
