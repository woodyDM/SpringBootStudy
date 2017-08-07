package com.wd;

import com.wd.security.CostomUserService;
import com.wd.security.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by wd on 2017/8/7.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    UserDetailsService customUserService(){
        return new CostomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/score").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/file").hasAuthority("ADMIN")
                .antMatchers("/jpa").hasAuthority("USER")
                .and()
                .formLogin()
                .loginPage("/login.html")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .and()
                .logout().permitAll();
        http.csrf().disable();
    }
    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }
}
