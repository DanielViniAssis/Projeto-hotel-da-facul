package com.projetohotel.hotel;

import com.projetohotel.hotel.view.HotelMenu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

	@Bean
	public CommandLineRunner run (){
		return args -> {
			HotelMenu.main(args);
		};
	}

}
