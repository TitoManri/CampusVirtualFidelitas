package com.fidelitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FidelitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FidelitasApplication.class, args);
                System.err.print(args);
	}

}
