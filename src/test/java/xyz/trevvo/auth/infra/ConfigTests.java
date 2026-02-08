package xyz.trevvo.auth.infra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import xyz.trevvo.auth.infra.cors.CorsConfig;
import xyz.trevvo.auth.infra.jackson.JacksonConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ConfigTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void shouldLoadCorsConfig() {
        assertNotNull(applicationContext.getBean(CorsConfig.class));
    }

    @Test
    void shouldLoadJacksonConfig() {
        assertNotNull(applicationContext.getBean(JacksonConfig.class));
    }
}
