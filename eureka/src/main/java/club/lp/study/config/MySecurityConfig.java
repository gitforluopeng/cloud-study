package club.lp.study.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        //忽略eureka相关请求
        http.csrf().ignoringAntMatchers("/eureka/**");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //
        super.configure(http);
        http.csrf().disable();/*.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();*/
    }
}
