package com.poynt.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.security.NoSuchAlgorithmException;

import static com.poynt.test.Authorization.generateToken;

@SpringBootApplication
@EnableOAuth2Client
public class TestApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		try {
			generateToken();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
