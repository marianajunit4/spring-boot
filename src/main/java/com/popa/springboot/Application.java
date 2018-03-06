package com.popa.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.popa.beans.AppConfig;

//launch the spring application
@SpringBootApplication
@ComponentScan("com.popa")
public class Application {

	public static void main(String[] args) {
		//using xml configuration
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//using java class configuration
		//ApplicationContext contextJava = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

	}

	//how the spring know to search for beans to manage?
	/**
	 * Spring will scan in the entire package where the Application class is declared - @SpringBootApplication
	 * @ComponentScan will happen at the launch time. Using this annotation you can specify the package to scan 
	 * (where spring looks for components and will create them and inject them where are needed)
	 * 
	 */
	//what are the annotation spring used?
	/**
	 * @Component, @Service, @Controller/ @RestController, @Repository - data layer
	 */
	
	//what if spring doesn't find what it is looking for?
		//it will throw and exception : NoSuchBeanDefinitionException
		/**
		 * org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'welcomeController': Unsatisfied dependency expressed through field 'service': No qualifying bean of type [com.popa.springboot.WelcomeService] found for dependency [com.popa.springboot.WelcomeService]: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [com.popa.springboot.WelcomeService] found for dependency [com.popa.springboot.WelcomeService]: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
		 */
	
	
	
	// spring-boot-starter-web
	/**
	 * - Starter for building applications with spring MVC.
	 * - Tomcat is the default embeddede container
	 * - used for rest web services
	 * - dependecies - tomcat, hibernate
	 * - what is auto-configuration ?  We need to manually configure the dispatcher servlet (a central servlet that dispatched request to controller and offers other functionality 
	 *	 that facilitates the development of web applications; integrated with the Spring IoC container and can use every other feature that Spring has.
	 * 	 The DispatcherServlet is an actual Servlet (it inherits from the HttpServlet base class), and as such is declared in the web.xml of your web application. You need to map requests that you want the DispatcherServlet to handle, by using a URL mapping in the same web.xml file. This is standard J2EE servlet configuration; the following example shows such a DispatcherServlet declaration and mapping:
	 * 	 https://docs.spring.io/spring/docs/3.0.0.M4/reference/html/ch15s02.html
	 */
}