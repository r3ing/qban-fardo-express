package com.reing.config.security;

import com.reing.config.filter.AuthorizationFilter;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserCredentialsSecurity userCredentialsSecurity;

    private final AuthorizationFilter authorizationFilter;

    public SecurityConfig(UserCredentialsSecurity userCredentialsSecurity, AuthorizationFilter authorizationFilter) {
        this.userCredentialsSecurity = userCredentialsSecurity;
        this.authorizationFilter = authorizationFilter;
    }

    //Helper method to create path matches with MVC
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, MvcRequestMatcher.Builder mvc) {
        try {
            http.csrf(AbstractHttpConfigurer::disable);
            http.authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(mvc.pattern("/login.xhtml")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/jakarta.faces.resource/**")).permitAll()
                            .anyRequest()
                            .authenticated();
                }
                    )
                    .formLogin(formLogin -> formLogin
                            .loginPage("/login.xhtml").permitAll()
                            .failureUrl("/login.xhtml?error=true")
                            .defaultSuccessUrl("/system/home.xhtml")
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login.xhtml")
                            .deleteCookies("JSESSIONID")
                    )
                    .exceptionHandling(ex -> ex.accessDeniedPage("/403.xhtml"))
                    .addFilterAt(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        } catch (Exception ex) {
            throw new BeanCreationException("Wrong spring security configuration", ex);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userCredentialsSecurity)
                .passwordEncoder(passwordEncoder());

        return builder.build();
    }*/




    //creates users in memory
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Crea un codificador de contraseñas
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // Crea un administrador de detalles de usuario en memoria
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Crea usuarios manualmente
        manager.createUser(User.builder()
                .username("admin")
                .password(encoder.encode("123"))  // Contraseña codificada
                .authorities("ROLE_ADMIN")  // Rol de admin
                .build());

        manager.createUser(User.builder()
                .username("user")
                .password(encoder.encode("123"))  // Contraseña codificada
                .authorities("ROLE_USER")  // Rol de usuario
                .build());

        // Devuelve el administrador de detalles de usuario con los usuarios definidos
        return manager;
    }
}
