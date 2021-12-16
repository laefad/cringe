package account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // private final ResourceServerProperties sso;

    // @Autowired
    // public ResourceServerConfig(ResourceServerProperties sso) {
    //     this.sso = sso;
    // }

    // @Bean
    // @ConfigurationProperties(prefix = "security.oauth2.client")
    // public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
    //     return new ClientCredentialsResourceDetails();
    // }

    // @Bean
    // public RequestInterceptor oauth2FeignRequestInterceptor(){
    //     return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
    // }

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                requestTemplate.header("Authorization", "Bearer " + details.getTokenValue());                   
            }
        };
    }

    // @Bean
    // public OAuth2RestTemplate clientCredentialsRestTemplate() {
    //     return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    // }

    // @Bean
    // public ResourceServerTokenServices tokenServices() {
    //     return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
    // }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();
    }
}
