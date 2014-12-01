package com.eco.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.eco.repositories")
@ComponentScan(basePackages = {"com.eco"})
public class ApplicationConfig{
 
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPackagesToScan(new String[] { "com.eco" });
 
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties());
 
      return em;
   }
 
//   @Bean
//   public DataSource dataSource(){
//      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//      dataSource.setUrl("jdbc:mysql://127.0.0.1:8889/testEco");
//      dataSource.setUsername( "root" );
//      dataSource.setPassword( "root" );
//      return dataSource;
//   }
   
   @Bean 
   public DataSource dataSource(){
	   BasicDataSource dataSource = new BasicDataSource();
	   dataSource.setUrl("jdbc:mysql://127.0.0.1:8889/testEco");
	   dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	   dataSource.setUsername( "root" );
	   dataSource.setPassword( "root" );
	   dataSource.setRemoveAbandoned(true);
	   dataSource.setInitialSize(20);
	   dataSource.setMaxActive(30);
	   //dataSource.setValidationQuery("Select 1");
	   //dataSource.setTestOnBorrow(true);
	   //dataSource.setTestOnReturn(true);
	   //dataSource.setTestWhileIdle(true);
	   
	   return dataSource;
   }
 
   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(emf);
 
      return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
 
   Properties additionalProperties() {
      Properties properties = new Properties();
      //properties.setProperty("hibernate.hbm2ddl.auto", "create");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
      properties.setProperty("hibernate.show_sql", "true");
      return properties;
   }
}