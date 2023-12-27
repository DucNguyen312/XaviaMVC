package com.example.admin.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AdminConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserConfigService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/image/**" , "/asset/**" , "/page/**" , "/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.POST , "/page/**").permitAll()
                .antMatchers(HttpMethod.GET , "/page/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/page/**").permitAll()
                .antMatchers(HttpMethod.DELETE , "/page/**").permitAll()
                .antMatchers(HttpMethod.GET,"/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/admin/**").hasAuthority("ADMIN")
                .and()
            .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/admin/do-login")
                .defaultSuccessUrl("/admin/")
                .permitAll()
                .and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                .logoutSuccessUrl("/admin/login")
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/error");

    }


}
