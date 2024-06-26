//package com.in28minutes.rest.webservices.restfulwebservices.jwt;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.util.UUID;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
//import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class JwtSecurityConfig {
//
//	  @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//	        return httpSecurity
//	                .csrf(csrf -> csrf
//	                        .ignoringAntMatchers("/h2-console/**")
//	                        .disable()) // Disable CSRF for H2 console
//	                .headers(headers -> headers
//	                        .frameOptions().disable()) // Allow H2 console to be displayed in a frame
//	                .sessionManagement(session ->
//	                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set session management to stateless
//	                .authorizeRequests(auth ->
//	                        auth.mvcMatchers("/authenticate", "/actuator", "/actuator/*").permitAll()
//	                                .antMatchers("/h2-console/**").permitAll() // Permit all requests to the H2 console
//	                                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//	                                .anyRequest().authenticated()) // Require authentication for all other requests
//	                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // Configure OAuth2 resource server
//	                .exceptionHandling(ex ->
//	                        ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//	                                .accessDeniedHandler(new BearerTokenAccessDeniedHandler())) // Configure exception handling
//	                .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic authentication
//	                .build();
//	    }
//    @Bean
//    public AuthenticationManager authenticationManager(
//            UserDetailsService userDetailsService) {
//        var authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        return new ProviderManager(authenticationProvider);
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("Priyanshu")
//                                .password("{noop}pass")
//                                .authorities("read")
//                                .roles("USER")
//                                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
//
//    @Bean
//    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
//        return new NimbusJwtEncoder(jwkSource);
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() throws JOSEException {
//        return NimbusJwtDecoder
//                .withPublicKey(rsaKey().toRSAPublicKey())
//                .build();
//    }
//
//    @Bean
//    public RSAKey rsaKey() {
//
//        KeyPair keyPair = keyPair();
//
//        return new RSAKey
//                .Builder((RSAPublicKey) keyPair.getPublic())
//                .privateKey((RSAPrivateKey) keyPair.getPrivate())
//                .keyID(UUID.randomUUID().toString())
//                .build();
//    }
//
//    @Bean
//    public KeyPair keyPair() {
//        try {
//            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(2048);
//            return keyPairGenerator.generateKeyPair();
//        } catch (Exception e) {
//            throw new IllegalStateException(
//                    "Unable to generate an RSA Key Pair", e);
//        }
//    }
//
//    @Bean
//    public JWKSource<SecurityContext> jwkSource() {
//        JWKSet jwkSet = new JWKSet(rsaKey());
//        return (((jwkSelector, securityContext)
//                        -> jwkSelector.select(jwkSet)));
//    }
//
//}
//
