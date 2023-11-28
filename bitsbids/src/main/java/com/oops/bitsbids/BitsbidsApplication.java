package com.oops.bitsbids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.print.DocFlavor;
import java.net.URI;
import java.util.Map;

@SpringBootApplication
@RestController
public class BitsbidsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitsbidsApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(16);
	}

	/*
	@CrossOrigin
	@GetMapping("/")
	public ResponseEntity<Void> redirect(@RequestParam Map<String,String> input) {
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:5173/")).build();
	}
	*/

}
