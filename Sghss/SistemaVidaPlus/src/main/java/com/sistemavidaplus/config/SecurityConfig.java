package com.sistemavidaplus.config;

import com.sistemavidaplus.entity.UsuarioEntity;
import com.sistemavidaplus.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public SecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF desativado para facilitar os testes no postman
                .authorizeHttpRequests(authz -> authz
                        // Permite acesso aos endpoints do Swagger
                        .requestMatchers(
                                "/swagger-ui/**",          // Interface do Swagger UI
                                "/v3/api-docs/**",         // Documentação OpenAPI v3 (formato JSON)
                                "/swagger-ui.html",        // Página principal do Swagger UI
                                "/swagger-resources/**",   // Recursos do Swagger
                                "/webjars/**"              // Arquivos web (CSS, JS) do Swagger
                        ).permitAll() // Permite todas as requisições para estes caminhos sem autenticação
                        .requestMatchers("/usuario/**", "/log/**").hasRole("ADMIN")
                        .requestMatchers("/paciente/**", "/consulta/**").hasAnyRole("ADMIN", "PROFISSIONAL")
                        .anyRequest().authenticated()) // Todas as outras requisições precisam de autenticação
                .httpBasic(Customizer.withDefaults()); // Usa autenticação HTTP Basic
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UsuarioEntity usuarioEntity = usuarioRepository.findByLogin(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if ("ADMIN".equalsIgnoreCase(usuarioEntity.getTipoUsuario())) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if ("PROFISSIONAL".equalsIgnoreCase(usuarioEntity.getTipoUsuario())) {
                authorities.add(new SimpleGrantedAuthority("ROLE_PROFISSIONAL"));
            }
            // Adicione outras roles se necessário

            return new org.springframework.security.core.userdetails.User(
                    usuarioEntity.getLogin(),
                    usuarioEntity.getSenha(),
                    authorities);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}