package it.uniroma3.siw.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder)
            .usersByUsernameQuery(
                "SELECT username, password, TRUE as enabled FROM credentials WHERE username = ?"
            )
            .authoritiesByUsernameQuery(
            	"SELECT c.username, u.role FROM credentials c JOIN users u ON c.utente_id = u.id WHERE c.username = ?"
            );
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // pagine pubbliche
                .requestMatchers("/", "/home", "/book/list", "/author/list", "/review/list",
                                 "/login", "/register",
                                 "/css/**", "/js/**", "/images/**", "/image/**", "/webjars/**", "/favicon.ico",
                                 "/error").permitAll()
                // ruoli
                
                .requestMatchers("/registered/**", "/review/selector", "/review/edit", "/review/editForm", "/review/add")
                    .hasAuthority("REGISTERED")
                    
                .anyRequest().hasAuthority("ADMIN")
                // tutte le altre richiedono login
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)   
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
        ;
        return http.build();
    }
}