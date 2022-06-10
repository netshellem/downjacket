package com.samplemgt.downjacket.configuration;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter
{
    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
//        grantedAuthorityMapper.setPrefix("ROLE_");
//
//        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
//        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
    }

    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout","/public","/403").permitAll();

//        http.authorizeRequests().antMatchers("/home/**").access("hasAnyRole('ROLE_user', 'ROLE_ADMIN')");
//        http.authorizeRequests().antMatchers("/jacket/**").access("hasAnyRole('ROLE_user', 'ROLE_ADMIN')");
//        http.authorizeRequests().antMatchers("/manpower/**").access("hasAnyRole('ROLE_user', 'ROLE_ADMIN')");

        http
                .authorizeRequests()
                .antMatchers("/home/**").hasAnyAuthority("user","admin")
                .antMatchers("/jacket/**").hasAnyAuthority("user","admin")
                .antMatchers("/manpower/**").hasAnyAuthority("user","admin")
                .antMatchers("/admin").hasAuthority("admin")
                .anyRequest().permitAll();
    }
}
