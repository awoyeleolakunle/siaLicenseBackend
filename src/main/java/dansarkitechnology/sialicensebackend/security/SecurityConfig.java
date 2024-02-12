package dansarkitechnology.sialicensebackend.security;

import dansarkitechnology.sialicensebackend.data.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final String[] allowedEndPoints = {
            "/api/v1/sialicence+/center/register" , "/api/v1/sialicence+/applicant/register",
           "/api/v1/sialicence+/center/createTrainingSession", "/api/v1/sialicence+/applicant/bookTraining",
            "/api/v1/sialicence+/center/aCenterAllTrainingSessions", "/api/v1/sialicence+/login",
            "/api/v1/sialicence+/trainingSession/allAvailableTrainingSessionUnderTrainingType",
            "/api/v1/sialicence+/applicant/applicantDetails", "/api/v1/sialicence+/blog/blogPostCreation",
            "/api/v1/sialicence+/blog/allActiveBlogPosts", "/api/v1/sialicence+/blog/allBlogPosts",
            "/api/v1/sialicence+/question/questionCreation"
    };

    private final String[] centerEndpoints = {
     //        "/api/v1/sialicence+/center/createTrainingSession"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((auth-> {auth.requestMatchers(allowedEndPoints).permitAll();}))
//                        .requestMatchers(centerEndpoints).hasAuthority(Roles.CENTER.name());}))
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
