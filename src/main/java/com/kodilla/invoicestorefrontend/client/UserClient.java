package com.kodilla.invoicestorefrontend.client;

import com.kodilla.invoicestorefrontend.domain.User;
import com.kodilla.invoicestorefrontend.config.BackendConfig;
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

public class UserClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserClient.class);
    private static UserClient userClient;

    private BackendConfig backendConfig = BackendConfig.getInstance();
    private RestTemplate restTemplate = new RestTemplate();


    private UserClient() {
    }

    public static UserClient getInstance() {
        if (userClient == null) {
            userClient = new UserClient();
        }
        return userClient;
    }

    public Set<User> getUsers() {

        URI url = getUrl();
        try {
            User[] usersResponse = restTemplate.getForObject(url, User[].class);
            return new HashSet<>(Arrays.asList(ofNullable(usersResponse).orElse(new User[0])));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new HashSet<>();
        }
    }

    private URI getUrl() {
        URI url = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendApiEndPoint() + "users/")
                .build().encode().toUri();
        return url;


    }

    public User getUser(Long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendApiEndPoint() + "users/" + userId)
                .build().encode().toUri();
        try {
            return restTemplate.getForObject(url, User.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new User();
        }
    }
}
