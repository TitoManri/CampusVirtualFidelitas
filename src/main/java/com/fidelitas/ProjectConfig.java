package com.fidelitas;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableWebSecurity
@ComponentScan("com.fidelitas")
public class ProjectConfig implements WebMvcConfigurer {

    //Los siguientes metodos son utilizados para el tema de internacionalizacion

    //LocaleResolver ubica la informacion del browser local del usuario y fija la informacion a desplegar
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    //Define cual sera la variable que cambia el idioma de los textos.
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    //Se agrega un interceptor para poder hacer el cambio de idioma de inmediato
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    //Se utilizara para recuperar los textos dentro de java segun el idioma
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasename("messages");
        bundleMessageSource.setDefaultEncoding("UTF-8");
        return bundleMessageSource;

    }
        
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/templates/admin/**","/templates/admin/apartadoClases/agregar").hasRole("ADMIN")
                .requestMatchers("/templates/**",
                        "/templates/plantillas/**","/templates/plantillas/footer_header","/templates/plantillas/popups") //Carpeta Plantillas
                    .hasAnyRole("ADMIN", "ESTUDIANTE")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .formLogin((form) -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/paginaprincipal")) // Redirige a /ruta después del inicio de sesión exitoso
            .logout((logout) -> logout.permitAll())
            .userDetailsService(userDetailsService)
            .csrf().disable();
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**","/webjars/**","/icons/**");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    public void configuredGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
}

