package pe.edu.cibertec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pe.edu.cibertec.dao.UsuarioService;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioService usuarioServicio;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Bean, hace que el metodo se registre en el contenedor del spring para que el usuario sea valido en memoria
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				"/registro**",
				"/login",
				"/**",
				"/busqueda**",
				"/venta-pasaje**",
				"/js/**",
				"/css/**",
				"/templates/**",
				"/imagenes/**",
				"/datepicker/**",
				"/controller**",
				"/alertifyjs/**").permitAll()
		.antMatchers("/pagina-principal/buscar**","/imgs/**","/css/**","/js/**","/fonts/**","/favicon.ico").permitAll()
		
		.anyRequest().authenticated()
		.and()
		
		.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/pagina-principal/")
        .failureUrl("/login?error=true")
        .permitAll()
    .and()
    .logout()
    .invalidateHttpSession(true)
	.clearAuthentication(true)
	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	.logoutSuccessUrl("/?logout")
	.permitAll()
	.and() 
	.csrf()
    .disable();
    
	}
	
	
}
