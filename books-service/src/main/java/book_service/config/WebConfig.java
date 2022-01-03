package book_service.config;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.nimbusds.jose.shaded.json.JSONArray;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	String jwkSetUri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> 
                authorize.anyRequest().permitAll()
            )
            .oauth2Login(oauth2LoginCustomizer -> 
                oauth2LoginCustomizer.userInfoEndpoint(userInfoEndpointCustomizer -> 
                    userInfoEndpointCustomizer.oidcUserService(
                        this.oidcUserService()
                    )
                )
            )
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .oauth2Client()
            ;
    }

    @Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
	}

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return (userRequest) -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);

            final Map<String, Object> claims = oidcUser.getClaims();
            final JSONArray groups = (JSONArray) claims.get("groups");

            final Set<GrantedAuthority> mappedAuthorities = groups.stream()
                    .map(role -> new SimpleGrantedAuthority(("ROLE_" + role)))
                    .collect(Collectors.toSet());

            return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        };
    }

}
