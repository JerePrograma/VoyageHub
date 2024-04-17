package ar.com.voyagehub.voyagehub;

import ar.com.voyagehub.voyagehub.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadWeb {

    final
    ClienteServicio clienteServicio;

    public SeguridadWeb(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clienteServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Desactivar CSRF para simplificar el ejemplo
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/logincheck", "/css/**", "/js/**", "/img/**", "/**").permitAll()  // Permite acceso a todos estos caminos sin autenticación
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Solo los usuarios con rol ADMIN pueden acceder a rutas bajo /admin/**
                        .anyRequest().authenticated()  // Todas las demás solicitudes requieren que el usuario esté autenticado
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/logincheck")
                        .usernameParameter("email")
                        .passwordParameter("contrasenia")
                        .defaultSuccessUrl("/", true)  // Redireccionar a la página principal después del login exitoso
                        .permitAll())  // Permitir acceso al formulario de login para todos
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")  // Redireccionar a la página principal después del logout
                        .permitAll());
        return http.build();
    }
}