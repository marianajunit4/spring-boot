package com.popa.beans;

import org.springframework.stereotype.Component;

@Component // annotation config approch
public class CubsTeam extends Team{
	public CubsTeam() {
		super();
		setName("Cubs Team");
	}
}
