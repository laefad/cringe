package account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .antMatchers("/").permitAll()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .oauth2ResourceServer()
                    .jwt()
                    .and()
                .and()
                .oauth2Client();
    }

}
