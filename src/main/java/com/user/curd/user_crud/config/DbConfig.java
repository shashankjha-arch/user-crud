package com.user.curd.user_crud.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean
    public DataSource createDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/global-ecommerce-solution");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        return  dataSource;
    }
}
