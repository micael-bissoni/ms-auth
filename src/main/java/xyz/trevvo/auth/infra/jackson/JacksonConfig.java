package xyz.trevvo.auth.infra.jackson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import tools.jackson.core.json.JsonReadFeature;

@Configuration
public class JacksonConfig {

    @Bean
    public JsonMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> builder.enable(
                JsonReadFeature.ALLOW_UNQUOTED_PROPERTY_NAMES,
                JsonReadFeature.ALLOW_SINGLE_QUOTES);
    }
}
