package dansarkitechnology.sialicensebackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
public class AppConfig {

    public static final String MAIL_SENDER_ACCOUNT = "awoyeleolakunle@gmail.com";
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.protocol}")
    private String mailProtocol;
    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailHost);
        javaMailSender.setPort(mailPort);
        javaMailSender.setProtocol(mailProtocol);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.ssl.enable", "true");
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setUsername(mailUsername);
        javaMailSender.setPassword(mailPassword);

        return javaMailSender;
    }
}
