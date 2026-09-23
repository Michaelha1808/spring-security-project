package mihchaelha1808.example.spring_security_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // Active spring web security
public class SecurityConfig {

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

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password(passwordEncoder().encode("222")) // raw
//                .roles("admin","user")
                .authorities("ROLE_admin","ROLE_user", "order:read","order:read")
                .build();

        UserDetails user = User.
                withUsername("user")
                .password(passwordEncoder().encode("111")) // raw
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
