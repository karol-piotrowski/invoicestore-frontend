package com.kodilla.invoicestorefrontend.client;

import com.kodilla.invoicestorefrontend.config.BackendConfig;
import com.kodilla.invoicestorefrontend.domain.EmailConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Optional.ofNullable;

public class EmailConfigClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConfigClient.class);
    private static EmailConfigClient emailConfigClient;

    private BackendConfig backendConfig = BackendConfig.getInstance();
    private RestTemplate restTemplate = new RestTemplate();

    private EmailConfigClient() {
    }

    public static EmailConfigClient getInstance() {
        if (emailConfigClient == null) {
            emailConfigClient = new EmailConfigClient();
        }
        return emailConfigClient;
    }

    public Set<EmailConfig> getEmailConfigs() {

        URI url = getUrl();
        try {
            EmailConfig[] emailConfigsResponse = restTemplate.getForObject(url, EmailConfig[].class);
            return new HashSet<>(Arrays.asList(ofNullable(emailConfigsResponse).orElse(new EmailConfig[0])));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new HashSet<>();
        }
    }

    private URI getUrl() {
        URI url = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendApiEndPoint() + "emailconfigs/")
                .build().encode().toUri();
        return url;
    }

    public EmailConfig getEmailConfig(Long emailConfigId) {
        URI url = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendApiEndPoint() + "emailconfigs/" + emailConfigId)
                .build().encode().toUri();
        try {
            EmailConfig emailConfigsResponse = restTemplate.getForObject(url, EmailConfig.class);
            return emailConfigsResponse;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new EmailConfig();
        }
    }
}
