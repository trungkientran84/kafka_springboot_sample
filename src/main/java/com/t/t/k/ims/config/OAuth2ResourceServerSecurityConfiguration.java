package com.t.t.k.ims.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

/**
 * Configuration class for OAuth2 Resource server
 *
 * @author ttkien
 */
@EnableWebSecurity
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.resource-server.jwt.jwk-set-uri}")
    String jwkSetUri;

    /**
     * Configures the Http security authorization for requests
     *
     * @param http Http Security object to configure
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests(request ->
                        request
                                .antMatchers("/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    }

    /**
     * Bean to get the jwt decoder by passing the OAuth server uri using Nimbus Jwt Decoder
     *
     * @return jwt decoder
     */
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }
}
