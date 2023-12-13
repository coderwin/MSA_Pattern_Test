package com.example.memberservice.config;


import com.example.memberservice.jwt.CustomJwtFilter;
import com.example.memberservice.jwt.JwtAccessDenied403Handler;
import com.example.memberservice.jwt.JwtAuthentication401Check;
import com.example.memberservice.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//웹보안을 활성화하겠다는 의미
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider provider;
    private final JwtAuthentication401Check errorhandler1;
    private final JwtAccessDenied403Handler errorhandler2;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //HttpServletRequest를 사용하는 모든 요청에 대해서 접근권한을 설정하겠다는 의미
        http
                .csrf(CsrfConfigurer :: disable) //csrf를 disable()설정과 동일
                .addFilterBefore(new CustomJwtFilter(provider),
                                    UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandler ->{
                    exceptionHandler.authenticationEntryPoint(errorhandler1)
                            .accessDeniedHandler(errorhandler2);
                })
                //세션을 사용하지 않을 것이므로 stateless로 설정
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authorizeHttpRequests(request -> {
                    // /api/hello에 대한 요청은 인증없이 접근할 수 있도록 허용
                    request.requestMatchers(
                                new AntPathRequestMatcher("/"),
                                    new AntPathRequestMatcher("/api/hello"),
                                    new AntPathRequestMatcher("/api/signin"),
                                    new AntPathRequestMatcher("/api/signup")
                            )
                            .permitAll()
                            //나머지요청에 대해서는 인증을 받도록 처리
                            .anyRequest().authenticated();
                });
        return http.build();
    }
    //정적리소스에  제외하기
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> {
            web.ignoring()
                    .requestMatchers(
                            PathRequest.toStaticResources().atCommonLocations()
                    ) ;
        };
    }
}
