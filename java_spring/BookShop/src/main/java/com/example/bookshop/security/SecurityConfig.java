package com.example.bookshop.security;

import com.example.bookshop.security.jwt.JWTRequestFilter;
import com.example.bookshop.security.oauth.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BookstoreUserDetailsService bookstoreUserDetailService;
    private final JWTRequestFilter jwtRequestFilter;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomLogoutHandler logoutHandler;
    private final AuthExceptionCatchFilter authExceptionCatchFilter;

    @Autowired
    public SecurityConfig(BookstoreUserDetailsService bookstoreUserDetailService, JWTRequestFilter jwtRequestFilter,
                          CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
                          CustomLogoutHandler logoutHandler, AuthExceptionCatchFilter authExceptionCatchFilter) {
        this.bookstoreUserDetailService = bookstoreUserDetailService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.logoutHandler = logoutHandler;
        this.authExceptionCatchFilter = authExceptionCatchFilter;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(bookstoreUserDetailService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/my", "/profile", "/api/bookReview", "/api/rateBook", "/api/rateBookReview")
                    .authenticated()//.hasRole("USER")
                .antMatchers("/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/signin")
                    .failureUrl("/signin").successHandler(customAuthenticationSuccessHandler)
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .addLogoutHandler(logoutHandler)
                    .logoutSuccessUrl("/signin").deleteCookies("token")
                .and().oauth2Login()
                .and().oauth2Client();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authExceptionCatchFilter, JWTRequestFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
