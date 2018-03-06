package com.popa.beans;

import java.text.NumberFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

/**
 * You can have many configuration class files
*/
@Configuration // java configuration config
// enable the aspect oriented programming in java using the proxies that stands before the beans
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean(initMethod="prepare", destroyMethod="goHome")
	@Scope("prototype")
	public Team redSox() {
		return new RedSox();
	}
	
	@Bean
	public Team blueSox() {
		return new BlueSox();
	}
	
	@Bean
	public NumberFormat nf() {
		return NumberFormat.getCurrencyInstance();
	}
	
	
}
