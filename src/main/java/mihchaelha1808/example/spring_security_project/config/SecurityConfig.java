package mihchaelha1808.example.spring_security_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Active spring web security
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        // tach backend vs frontend
        http.formLogin((formLogin)-> formLogin.loginProcessingUrl("/login"));

        http.authorizeHttpRequests(req -> req
                .requestMatchers("/api/v1/auth/login","/api/v1/auth/register")
                .permitAll()
//                .requestMatchers("/v1/admin/normal").hasAnyRole("ADMIN","USER")
//                .requestMatchers("/v1/admin/vip").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
        );
        return http.build();
    }

    /**
     * Config User information
     * @return
     * */
    @Bean
    public UserDetailsService userDetailsService(){


//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("222") // raw
//                .roles("admin","user")
//                .build();
//
//        UserDetails user = User.
//                 withUsername("user")
//                .password("{noop}111") // raw
//                .roles("user")
//                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("123")) // raw
//                .roles("admin","user")
                .authorities("ROLE_ADMIN","ROLE_USER")
                .build();

        UserDetails user = User.
                withUsername("user")
                .password(passwordEncoder().encode("123")) // raw
//                .roles("user")
                .authorities("ROLE_USER")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
