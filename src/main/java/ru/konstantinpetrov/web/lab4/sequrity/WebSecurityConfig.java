package ru.konstantinpetrov.web.lab4.sequrity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.konstantinpetrov.web.lab4.service.UsersDet;
import ru.konstantinpetrov.web.lab4.Lab4Application.*;




@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig implements WebMvcConfigurer {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http.authorizeRequests()
                .mvcMatchers("/compile", "/all-data")
                .hasAnyRole("USER")
                .mvcMatchers("/login", "/users")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/users")
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .httpBasic().and().build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
