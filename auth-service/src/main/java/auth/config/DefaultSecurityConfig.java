package auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class DefaultSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .formLogin(
                    withDefaults()
                );
        return http.build();
    }

    // @Bean
    // UserDetailsService users() {
    //     return new UserDetailsServiceImpl();
    //     // UserDetails user = User.withDefaultPasswordEncoder()
    //     //   .username("admin")
    //     //   .password("password")
    //     //   .roles("USER")
    //     //   .build();
    //     // return new InMemoryUserDetailsManager(user);
    // }

}
