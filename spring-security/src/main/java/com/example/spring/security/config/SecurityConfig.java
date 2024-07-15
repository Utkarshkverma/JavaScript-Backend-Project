package com.example.spring.security.config;

import com.example.spring.security.model.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(registry -> {
            registry.requestMatchers("/home", "/register/**", "/authenticate").permitAll();
            registry.requestMatchers("/admin/**").hasRole("ADMIN");
            registry.requestMatchers("/user/**").hasRole("USER");
            registry.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User
//                .withUsername("User1")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User
//                .withUsername("admin1")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails adminUser = User
//                .withUsername("admin2")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin, adminUser);
//    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
