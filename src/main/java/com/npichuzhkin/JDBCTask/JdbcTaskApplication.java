package com.npichuzhkin.JDBCTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class JdbcTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcTaskApplication.class, args);
	}

}
