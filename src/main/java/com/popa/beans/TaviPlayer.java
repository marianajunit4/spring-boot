package com.popa.beans;

import org.springframework.stereotype.Component;

@Component
public class TaviPlayer extends Player{
	@Override
	public String getName() {
		return "Tavi";
	}
}
