package com.db.polling.domain.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class AppConfigTest {

    @Autowired
    private TimeSessionProperties timeSessionProperties;

    @Test
    void testTimeSessionPropertiesConfigured() {
        assertNotNull(timeSessionProperties);
    }

}
