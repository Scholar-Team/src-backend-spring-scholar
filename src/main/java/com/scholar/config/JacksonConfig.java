package com.scholar.config;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

@Configuration
public class JacksonConfig {

	public static final DateTimeFormatter ISO_FIXED_FORMAT =
	        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
                jackson2ObjectMapperBuilder.serializers(new ZonedDateTimeSerializer(ISO_FIXED_FORMAT));
            }
        };
    }

}
