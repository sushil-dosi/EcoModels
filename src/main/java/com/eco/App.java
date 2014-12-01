package com.eco;



import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.eco.config.ApplicationConfig;
import com.eco.entities.Employee;
import com.eco.repositories.EmployeeRepository;

/**
 * 
 *
 */
@Component
public class App 
{
	public static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext ctx = 
    			   new AnnotationConfigApplicationContext();
    	ctx.register(ApplicationConfig.class);
    	ctx.refresh();
    	logger.info("Got Application context");
    	//EmployeeService empService = ctx.getBean(EmployeeService.class);
    	EmployeeRepository empRep = ctx.getBean(EmployeeRepository.class);
    	logger.info("Getting the bean");
    	Employee employee = new Employee("Sushil","Dosi");
    	empRep.save(employee);
    	//empService.addEmployee(employee);
    	logger.info("saved employee");
    	
    }
}
