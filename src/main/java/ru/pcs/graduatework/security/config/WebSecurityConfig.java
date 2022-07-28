package ru.pcs.graduatework.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


// WebSecurityConfigurerAdapter описывает как работает Security у нас в приложении
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    // Здесь мы еще раз уточняем у кого спрашивать пользователя из базы
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        System.out.println(userDetailsService);
        // csrf-защита - это защита, которая защищает страницы веб-приложения от вредоносных страниц страниц
        http.csrf().disable();

        http
//                .rememberMe() // здесь мы указываем, что теперь мы будем использовать функцию rememberMe
                // она позволяет после перезапуска программы оставаться в системе
//                    .rememberMeParameter("rememberMe")   // в этом параметре на фронте (страница signIn)
                // передается информация нужно ли запоминать пользователя в системе
//                    .tokenRepository(tokenRepository())  // здесь указывается инфомрация откуда брать токены для входа в систему
                // при перезапуске сессии. Эта информация хранится в базе данных
//                    .tokenValiditySeconds(60 * 60 * 24 * 365)  // этот параметр определяет сколько по времени
                // будет хранится информация о входе в базе данных
//                    .and()
                .authorizeRequests()
                .antMatchers("/signUp").permitAll()   // страница /signUp доступна всем
                .antMatchers("/portfolio").authenticated()   // страница portfolio доступна только аутонтефицированным пользователям
                .antMatchers("/portfolio/**").authenticated()   // страница portfolio доступна только аутонтефицированным пользователям
                .antMatchers("/accounts/**").hasAuthority("ADMIN")  // к страницам вида /accounts/** могут получить
                // доступ только администраторы
                .and()
                // используем and() для того, чтобы сделать свою форму логина
                .formLogin()
                .loginPage("/signIn")
//                    .loginProcessingUrl("/portfolio")
                .defaultSuccessUrl("/portfolio", true)
                .failureUrl("/signIn?error")
                .usernameParameter("login") /* со страницы signIn.ftlh в качестве логина уходит параметр
                name="login". Если в качестве username мы хотим использовать email, то нужно указать spring'у,
                чтобы он воспринимал параметр name="email" как username - для этого и используется .usernameParametr*/
                .passwordParameter("password") /* аналогично как с usernameParametr нужно указать какой параметр
                на странице signIn.ftlh Spring должен воспринимать как password */
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/portfolio_logout")
                .logoutSuccessUrl("/welcome.html");
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }


}
