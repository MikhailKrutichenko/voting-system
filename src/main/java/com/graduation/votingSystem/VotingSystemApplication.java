package com.graduation.votingSystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.graduation.votingSystem.converters.Formatters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
public class VotingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingSystemApplication.class, args);
    }

    @Configuration
    static class AppConfiguration implements WebMvcConfigurer {

        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addFormatter(new Formatters.DateFormatter());
            registry.addFormatter(new Formatters.TimeFormatter());
        }

        @Override
        public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
            for (HttpMessageConverter converter : converters) {
                if (converter instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter) {
                    ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                    mapper.registerModule(new Hibernate5Module());
                }
            }
        }
    }
}
