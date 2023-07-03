package com.gpm22.WebJavaH2DataBase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
