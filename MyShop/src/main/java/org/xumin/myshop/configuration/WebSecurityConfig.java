package org.xumin.myshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/css/**", "/image/products/**", "/js/**", "/webfonts/**").permitAll()
                        .requestMatchers("/main/home", "/main/login", "/main/logout","/main/register").permitAll()
                        .requestMatchers("/main/detail/**","/sort/**","/path/**","/main/search").permitAll()
                        .requestMatchers("/api/cart/**").permitAll()
                        .requestMatchers("/main/payment","/cart/item/delete/**","/cart/delete","/main/orders","/account/**").hasRole("MEMBER")
                        .requestMatchers("/admin/**","/employee/**").hasRole("ADMIN")
                        .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/main/login")
                        .usernameParameter("email_phone")
                        .passwordParameter("pass")
                        .defaultSuccessUrl("/main/home")
                        .failureUrl("/main/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}