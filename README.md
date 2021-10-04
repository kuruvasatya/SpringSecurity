# SpringSecurity

## Dependency
- spring-boot-starter-security
- jdbc api => for db connection

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
public class AppSecurity extends WebSecurityConfigurerAdapter{
	
	// for Authentication purpose
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("user")
				.roles("USER")
				.and()
				.withUser("admin")
				.password("admin")
				.roles("ADMIN");
				
	}
	
	// for Authorization Purpose
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER", "ADMIN")
			.antMatchers("/").permitAll().and().formLogin();
	}
	
	// for password encoding
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
```

## Authentication process
![](authentication.png)

- Authentication manager is used to manage different types of authentiation providers ex LDAP, OAuth
- Authentication managet gets Authentication Object with credentials and if authentication is success then Authentication managet sends Authentication object with principal
- Authentication provider has supports() method which tells which kind of authentication it provides
- Authentication provider takes the request and gives it to UserDetailsService which may connect to any type like text file or db to fetch user details.

## default Schema created
```sql
create table users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled boolean not null
);

create table authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
```
## LDAP Dependencies
- unboundid-ldapsdk
- spring-ldap-core
- spring-security-ldap

## LDAP configuration
- spring.ldap.embedded.port=8389
- sprind.ldap.embeded.ldif=classpath:ldap-data.ldif
- spring.ldap.embeded.base-dn=dc=springframework,dc=org

## Sample ldif file
- source: https://spring.io/guides/gs/authenticating-ldap/
