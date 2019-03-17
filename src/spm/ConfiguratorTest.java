package spm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfiguratorTest {

    @Test
    public void createConfiguratorAndContactServer() throws Exception {
        // GIVEN server url string.
        final String urlString = "http://localhost:8080";

        // WHEN create configurator with given urls string.
        Configurator proxyConfig = new Configurator(urlString);

        // THEN the object is not null.
        assertNotNull(proxyConfig);
    }

    @Test
    public void createConfiguratorWithNonExistingServerUrlThrows() throws Exception {
        // GIVEN not existing server url string.
        final String urlString = "http://not_existing_server";

        // WHEN create configurator with given urls string.
        // THEN RuntimeException is thrown.
        Assertions.assertThrows(RuntimeException.class, () -> {
            Configurator proxyConfig = new Configurator(urlString);
        });
    }

}