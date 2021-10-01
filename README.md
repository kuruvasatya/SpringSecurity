# SpringSecurity

## Dependency
- spring-boot-starter-security

## Annotations
- @EnableWebSecurity

## Spring Security Behaviour
- Adds mandatory authentication for URL
- Adds login form
  - username = user
  - password = provided in console
  - can configure in application.properties file
```xml
spring.security.user.name = name
spring.security.user.password = password
```

## Configuring the work done by AuthenticationManager
- The work done by the authentication manager is written in class called *WebSecurityConfigureAdapter* (present in SpringSecurityApp) which has method called *configure(AuthenticationMangaerBuilder)*
- Need to extend this class and override that method for customising the authentication
- look into second project for more details
```java
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("Satya")
				.password("user")
				.roles("USER");
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
```
