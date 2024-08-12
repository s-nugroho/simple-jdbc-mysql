package com.testing.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(JdbcApplication.class);

		try	{
			if (args.length > 0) {
				application.setAdditionalProfiles(args);
			}

			application.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		SpringApplication.run(JdbcApplication.class, args);
	}
}
