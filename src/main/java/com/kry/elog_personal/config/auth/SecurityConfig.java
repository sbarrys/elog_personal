package com.kry.elog_personal.config.auth;


import com.kry.elog_personal.common.Role;
import com.kry.elog_personal.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
//Spring Security 설정들을 활성화시켜 줍니다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()

                //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .headers().frameOptions().disable()
                .and()
                //URL별 권한 관리를 설정하는 옵션의 시작
                //authorizeRequests가 선언되어야만 antMatchers 옵션 사용가능
                .antMatcher("/**").authorizeRequests()
                //권한 관리 대상 지정
                //"/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 가능
                .antMatchers("/api", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**", "/error/**").permitAll()
                .antMatchers("/home/**").hasRole(Role.USER.name())
                .antMatchers("/boards/**").permitAll()/*hasRole(Role.USER.name())*/
                .antMatchers("/index/**").permitAll()
                .antMatchers("/favicon.ico").permitAll();


//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()//로그인 이후 사용자 정보 가져올때 의 설정들 담당
//                .userService(customOAuth2UserService).and().defaultSuccessUrl("/auth/login_success"); // 로그인 이후 후속조치 진행할 UserService객체 등록.
//        //SNS로 부터 User정보를 가지고와서 들고있는 상태. 후속조치를 취할 수 있다.
//        //여기서 프론트로 보내는건가?
    }
}
