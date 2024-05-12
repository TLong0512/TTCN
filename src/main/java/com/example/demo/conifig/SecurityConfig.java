package com.example.demo.conifig;

import com.example.demo.service.impl.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Bean
    BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws  Exception
    {
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests((auth)
                ->auth.requestMatchers("/*").permitAll().
                requestMatchers("/admin/**").hasAuthority("admin").
                anyRequest().authenticated()).formLogin(login->login.loginPage("/logon").
                loginProcessingUrl("/logon").usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/admin",true))
                .logout(logout->logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"))
                .logout();
        return  http.build();
    }
    @Bean
    WebSecurityCustomizer webSecurityCustomizer()
    {
        return  (web) -> web.ignoring().requestMatchers("/admin/assets/**");
    }
    @Bean
    WebSecurityCustomizer webSecurityCustomizer2()
    {
        return  (web) -> web.ignoring().requestMatchers("/user/assets/**");
    }
}
