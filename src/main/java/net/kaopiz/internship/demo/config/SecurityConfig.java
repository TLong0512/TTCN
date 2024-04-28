package net.kaopiz.internship.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	// Mã hóa mật khẩu
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()

				.authorizeHttpRequests(authorize -> authorize

						.requestMatchers(new AntPathRequestMatcher("/ad/1/home"))
						.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")

						.requestMatchers(new AntPathRequestMatcher("/ad/**")).hasRole("ADMIN")

						.requestMatchers(new AntPathRequestMatcher("/mn/**")).hasAnyRole("ADMIN", "MANAGER")

						.requestMatchers(new AntPathRequestMatcher("/emp/**"))
						.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")

						.requestMatchers(new AntPathRequestMatcher("/")).hasRole("USER").anyRequest().permitAll())

				// Đăng nhập thành công sẽ quay lại trang home
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/home")
						.permitAll())

				// Đăng xuất trở về trang home
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
						.logoutSuccessUrl("/home").invalidateHttpSession(true).deleteCookies("JSESSIONID")

				// Khi người dùng cố gắng truy cập trang admin sẽ chuyển về trang 404
				).exceptionHandling(exceptionHandling -> exceptionHandling
						.accessDeniedHandler((request, response, accessDeniedException) -> {
							if (request.isUserInRole("USER")) {
								response.sendRedirect("/not_found");
							} else {
								response.sendRedirect("/not_found");
							}
						}));
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}