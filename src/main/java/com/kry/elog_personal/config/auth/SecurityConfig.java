package com.kry.elog_personal.config.auth;


import com.kry.elog_personal.common.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
//Spring Security 설정들을 활성화시켜 줍니다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .headers().frameOptions().disable()
                .and()
                    //URL별 권한 관리를 설정하는 옵션의 시작
                    //authorizeRequests가 선언되어야만 antMatchers 옵션 사용가능
                .antMatcher("/**").authorizeRequests()
                    //권한 관리 대상 지정
                    //"/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 가능
                .antMatchers("/api", "/css/**", "/images/**",
                         "/js/**", "/h2-console/**","/error/**").permitAll()
                .antMatchers("/home/**").hasRole(Role.USER.name())
                .antMatchers("/boards/**").permitAll()/*hasRole(Role.USER.name())*/
                .antMatchers("/index/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()

    .anyRequest().anonymous()

                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())

                .and()
                    //로그아웃 기능에 대한 설정진입
                    .logout()
                    //로그아웃 성공시 다음주소로 재연결
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("SESSION")
                    .deleteCookies("JSESSIONID")
                    .deleteCookies("SPRING_SECURITY_REMEMBER_ME_COOKIE")
                    .logoutSuccessUrl("/").permitAll()

                .and()
                    //ouath2로그인 설정 진입
                    .oauth2Login()
                    //oauth2 로그인 성공 이후 사용자 정보 가져올떄의 설정
                    .userInfoEndpoint()
                    //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록합니다.
                    //리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있습니다.
                    .userService(customOAuth2UserService);

    }


}
