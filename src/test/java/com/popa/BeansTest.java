package com.popa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.popa.beans.AppConfig;
import com.popa.beans.BlueSox;

//run the application context and catch it. No need to fire it over and over again
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
// every test will run into a transaction and will rollback when is finish
// very useful in database tests
@Transactional
public class BeansTest {
	
	@Autowired
	private BlueSox bluesox;

	/* - no need anymore with the runWith annotation
	@Before
	public void setUp() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		bluesox = ctx.getBean("blueSox", BlueSox.class);
	}*/
	
	@Test
	public void testMarianaPlayer() throws Exception {
		String name = bluesox.getName();
		assertEquals(name, "Blue Sox");
	}
}


/**
 * Transaction
 * ACID Properties :
 * - atomic - all or nothing (when credit card falls)
 * - consistent - DB integrity never violated (foring key)
 * - isolated -  how transactions see work done by others
 * - durable - committed changes are permanent
 * 
 * Transaction in Spring (@Transactional)
 * - will be use the transaction manager of the database
*/