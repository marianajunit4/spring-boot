package com.popa.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * You can have many configuration class files
*/
@Configuration // java configuration config
public class AppConfig {
	
	@Bean
	public Team redSox() {
		return new RedSox();
	}
	
	@Bean
	public Team blueSox() {
		return new BlueSox();
	}
}
