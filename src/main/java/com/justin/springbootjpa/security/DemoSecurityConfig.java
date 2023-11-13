package com.justin.springbootjpa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // 方法3 (non-hard-coding) this is in db (users -< authorities) both tables' names have to be exact as it is
    // tell spring security to use JDBC authentication with datasource
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    // https://www.luv2code.com/generate-bcrypt-password
    // db pwd: ooxx


// 方法2 (hard-coding)
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests(configurer ->
//                configurer
//                        .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
//        );
//
//        // use HTTP Basic authentication
//        httpSecurity.httpBasic(Customizer.withDefaults());
//
//        // disable Cross Site Request Forgery (CSRF) -> rest api doesn't need it
          // if you are building a rest api for non-browser clients, you may want to disable CSRF protection (CSRF is for web apps in general)
//        httpSecurity.csrf(csrf -> csrf.disable());
//
//        return httpSecurity.build();
//    }




// 方法1  this is in memory
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}1234")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}1234")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}1234")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john,mary,susan);
//    }

}
