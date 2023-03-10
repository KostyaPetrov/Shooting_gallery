package ru.konstantinpetrov.web.lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Lab4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }
}
