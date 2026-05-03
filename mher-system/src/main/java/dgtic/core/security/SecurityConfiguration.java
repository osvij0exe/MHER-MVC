package dgtic.core.security;

import dgtic.core.service.securityService.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    //private UserDetailsService uds;
    private UserDetailsServiceImpl uds;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        JWTAuthenticationFilter jwtFilter = new JWTAuthenticationFilter(tokenProvider, uds);
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/static/**","/bootstrap/**", "/iconos/**","/image/**","/tema/**","plantillas/**","principal/**","/security/**").permitAll()
                        .requestMatchers("/paginas/citas/**","/paginas/pacientes/**").authenticated()
                        .requestMatchers("/paginas/pacientes/**").hasAuthority("USER")
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/security/login") //new
                        .loginProcessingUrl("/security/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/",true)
//                        .successForwardUrl("/security/login_success_handler")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/doLogout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true))
        ;
        return http.build();
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11, new SecureRandom());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(uds);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }



//    @Bean
//    UserDetailsManager inMemoryUserDetailsManager() {
//        //                                   password= password
//        var user1 = User.withUsername("user").password("$2y$11$.YNxoORZ6/BInEsxrwMqT.q5PToEp3QIjLeudxqOD7yCgVAkx62b2").roles("USER").build();
//        var user2 = User.withUsername("admin").password("$2y$11$.YNxoORZ6/BInEsxrwMqT.q5PToEp3QIjLeudxqOD7yCgVAkx62b2").roles("USER", "ADMIN").build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        //your AuthenticationProvider must return UserDetails object
        return new ProviderManager(authenticationProvider());
    }

}
