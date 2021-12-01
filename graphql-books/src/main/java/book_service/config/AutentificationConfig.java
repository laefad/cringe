package book_service.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;


// @Configuration
// public class AutentificationConfig {

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder(10);
//     }

//     @Bean
//     public AuthenticationProvider authenticationProvider(
//         UserDetailsService userService, 
//         PasswordEncoder passwordEncoder
//     ) {
//         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//         provider.setUserDetailsService(userService);
//         provider.setPasswordEncoder(passwordEncoder);
//         return provider;
//     }
// }
