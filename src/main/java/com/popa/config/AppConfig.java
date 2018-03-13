package com.popa.config;

import java.text.NumberFormat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.popa.beans.BlueSox;
import com.popa.beans.RedSox;
import com.popa.beans.Team;

/**
 * You can have many configuration class files
*/
@Configuration // java configuration config
// enable the aspect oriented programming in java using the proxies that stands before the beans
@EnableAspectJAutoProxy
// used to autoconfigure the database - when is not yet declared
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//enables spring's annotation-driven transaction management capability
@EnableTransactionManagement
//@EnableJpaRepositories("com.popa.repositories")
@PropertySource("classpath:prod.properties")
public class AppConfig {
	
	//reads the property source, prod.properties
	@Autowired
    private Environment env;
	
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
	
	
	//2 database configuration with the same bean name, depending on profile/enviroment (test vs prod)
	@Bean(name = "dataSource", destroyMethod = "shutdown")
	@Profile("test")
	public DataSource dataSourceForTest() {
		//creates a in-memory database, will use the provided structure in schema.sql file with the data provided in data.sql
		return new EmbeddedDatabaseBuilder().generateUniqueName(true).setType(EmbeddedDatabaseType.H2)
				.setScriptEncoding("UTF-8").ignoreFailedDrops(true)
				//schema.sql - will be runned by default by the datasource initializer
				.addScript("schemaTable.sql")
				//data.sql - is searched by Spring and executed by the data source initializer
				.addScripts("dataTable.sql")
				.build();
	}

	/*
	@Bean(name = "transactionManager")
	@Profile("test")
	public PlatformTransactionManager transactionManagerForTest() {
		return new DataSourceTransactionManager(dataSourceForTest());
	}*/

	
	@Bean(name = "dataSource")
    @Profile("prod")
    public DataSource dataSourceForProd() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.pass"));
        return dataSource;
    }
/*
    @Bean(name = "transactionManager")
    @Profile("prod")
    public PlatformTransactionManager transactionManagerForProd() {
        return new DataSourceTransactionManager(dataSourceForProd());
    }
*/}
