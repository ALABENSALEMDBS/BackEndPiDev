package com.example.pidevbackendproject.Config;

/*
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
 */
public class WebSecurityConfig {
/*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring().requestMatchers(
                    HttpMethod.POST,
                    "/public/**",
                    "/users"
            );

            web.ignoring().requestMatchers(
                    HttpMethod.GET,
                    "/public/**"
            );

            web.ignoring().requestMatchers(
                    HttpMethod.DELETE,
                    "/public/**",
                    "/users/{id}"
            );


            web.ignoring().requestMatchers(
                    HttpMethod.PUT,
                    "/public/**",
                    "/users/{id}/send-verification-email",
                    "/users/forgot-password"
                    //,"/roles/assign/users/"
            );


            web.ignoring().requestMatchers(
                            HttpMethod.OPTIONS,
                            "/public/**"
                    )
                    .requestMatchers(
                            "/v3/api-docs/**" , "/configuration/**" , "/swagger-ui/**",
                            "/swagger-resources/**" ,"/swagger-ui.html", "/webjars/**" , "/api-docs/**"
                    );

        };
    }
*/



}
