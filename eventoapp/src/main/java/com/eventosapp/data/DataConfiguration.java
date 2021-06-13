package com.eventosapp.data;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration

public class DataConfiguration {

	@Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
       /* dataSource.setDriverClassName("com.mysql.jdbc.Driver");*/
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/eventosapp");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
	
	/*
	 * spring.jpa.hibernate.ddl-auto=update
	 * spring.datasource.url=jdbc:mysql://localhost:3306/cobranca
	 * spring.datasource.username=root spring.datasource.password=root
	 * 
	 * spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
	 * spring.jpa.show-sql=true
	 */
	
	
	
	
	
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		/* spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect; */
		adapter.setPrepareConnection(true);
		return adapter;
	}
}
