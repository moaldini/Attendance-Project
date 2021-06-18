package edu.miu.attendance.config;

import edu.miu.attendance.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("JPAPersonDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticate/*").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/faculty/**").hasAuthority("ROLE_FACULTY")
                .antMatchers("/student/**").hasAuthority("ROLE_STUDENT")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        //Those two settings below is to enable access h2 database via browser
        http.csrf().disable();
        http.headers().frameOptions().disable();
        /*http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/", "/index", "/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_FACULTY")
                .antMatchers("/attendance/**").hasAuthority("ROLE_STUDENT")
                .anyRequest()
                .authenticated() //all other urls can be access by any authenticated role
                .and()
                    .formLogin() //enable form login instead of basic login
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/welcome")
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and()
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**") //don't apply CSRF protection to /h2-console
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access-denied")
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf().disable()
                    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        ;*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}