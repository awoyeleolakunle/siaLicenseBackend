package dansarkitechnology.sialicensebackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import java.time.Duration;
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

//    @Value("${spring.data.redis.host}")
//    private String redisHost;

//    @Value("${spring.data.redis.port}")
//    private int redisPort;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JavaMailSender javaMailSender() {
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

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(){
        return (builder )-> builder.
                withCacheConfiguration("ExamCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(60)));
    }
}

//     @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//        return new JedisConnectionFactory();
//     }

//      @Bean
//      public JedisPool jedisPool(){
//          return new JedisPool("localhost", 6379);
//      }

//      @Bean
//      public RedisConnectionFactory redisConnectionFactory(){
//        return new LettuceConnectionFactory(redisHost, redisPort);
//      }

//     @Bean
//    public RedisTemplate<String , Object> redisTemplate(){
//        RedisTemplate<String, Object>  template = new RedisTemplate<>();
//        template.setConnectionFactory((redisConnectionFactory()));
//        return template;
//     }
//}
