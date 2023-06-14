package cz.upce.NNPIA_Cmilanska_SEM_BE.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.NNPIA_Cmilanska_SEM_BE.services.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final AppUserDetailsService appUserDetailsService;

    @Autowired
    private final JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(
                        "/auth/**",
                        "/book").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
                    Map<String, Object> responseMap = new HashMap<>();
                    ObjectMapper mapper = new ObjectMapper();
                    response.setStatus(401);
                    responseMap.put("error", authException.getMessage());
                    responseMap.put("message", "Unauthorized.");
                    response.setHeader("content-type", "application/json");
                    String responseMsg = mapper.writeValueAsString(responseMap);
                    response.getWriter().write(responseMsg);
                })
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}