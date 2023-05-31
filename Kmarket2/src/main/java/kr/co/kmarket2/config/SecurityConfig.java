package kr.co.kmarket2.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SecurityUserService securityUserService;
	
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	// 접근권한 설정
        http.authorizeHttpRequests()
        	.anyRequest().permitAll();
        http.formLogin(login -> login
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login?success=100")
                .usernameParameter("uid")
                .passwordParameter("pass"));

        // 사이트 위조 방지 설정
        http.csrf(csrf -> csrf.disable());
        http.logout(logout -> logout
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/member/login?success=200"));
        http.rememberMe(me -> me
                .userDetailsService(securityUserService)
                .tokenRepository(tokenRepository())
                .tokenValiditySeconds(600));
        
//        http.exceptionHandling().accessDeniedPage("/accessDenied");
//        	.accessDeniedPage("/accessDenied");
        
        return http.build();
    }
	
    // 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
    	// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	return new BCryptPasswordEncoder();
    }
    
    // JDBC 기반의 tokenRepository 구현체
    @Bean
    PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource); // dataSource 주입
        return jdbcTokenRepository;
    }
}
