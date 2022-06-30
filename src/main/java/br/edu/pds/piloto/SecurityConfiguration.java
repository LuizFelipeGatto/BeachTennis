//package br.edu.pds.piloto;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public static BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests().antMatchers("/css/**", "/resources/**", "/static/**", "/webjars/**").permitAll()
//                .antMatchers("/listarUsuario", "/cadastrarUsuario").access("hasAuthority('ADMIN')").anyRequest().authenticated()
//                .and().formLogin().permitAll().and().logout().permitAll().and().httpBasic();
//
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("luizfelipe").password(passwordEncoder().encode("2022")).roles("ADMIN")
//                .and().withUser("user").password(passwordEncoder().encode("password")).roles("USER");
//    }
//}
