package ru.gb.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//    converter.setJwtGrantedAuthoritiesConverter(source -> {
//      Map<String, Object> resourceAccess = source.getClaim("realm_access");
//      List<String> roles = (List<String>) resourceAccess.get("roles");
//
//      return roles.stream()
//        .map(SimpleGrantedAuthority::new)
//        .collect(Collectors.toList());
//    });

    return httpSecurity
      .authorizeHttpRequests(configurer -> configurer
        .requestMatchers("/ui/issues/**").hasAuthority("ROLE_ADMIN")
        .requestMatchers("/ui/readers/**").hasAuthority("ROLE_USER")
        .requestMatchers("/ui/books/**").authenticated()
        .anyRequest().permitAll()
      )
      .formLogin(Customizer.withDefaults())
//      .oauth2ResourceServer(configurer -> configurer
//        .jwt(jwtConfigurer -> jwtConfigurer
//          .jwtAuthenticationConverter(converter))
//      )
      .build();
  }


}
