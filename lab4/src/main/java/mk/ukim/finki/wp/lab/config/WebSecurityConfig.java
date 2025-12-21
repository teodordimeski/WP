package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
            )
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/home", "/assets/**", "/register", "/dishes", "/listChefs", "/chefDetails", "/dishes/select-chef").permitAll()
                // only ADMIN can access add/edit/delete and other modifying endpoints
                .requestMatchers("/dishes/add", "/dishes/dish-form", "/dishes/dish-form/*", "/dishes/edit/**", "/dishes/delete/**", "/dishes/add-to-chef").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/dishes/add", "/dishes/edit/**", "/dishes/add-to-chef").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // use the default Spring Security login page (no custom loginPage)
                .permitAll()
                .defaultSuccessUrl("/dishes", true)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
    
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager( admin);
    }
}
