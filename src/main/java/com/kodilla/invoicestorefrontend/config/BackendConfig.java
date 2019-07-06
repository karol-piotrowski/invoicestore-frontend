package com.kodilla.invoicestorefrontend.config;

public class BackendConfig {
    private static BackendConfig backendConfig;

    private static final String backendApiEndPoint = new String("http://localhost:8080/v1/");

    private BackendConfig() {
    }

    public static BackendConfig getInstance() {
        if (backendConfig == null) {
            backendConfig = new BackendConfig();
        }
        return backendConfig;
    }

    public String getBackendApiEndPoint() {
        return backendApiEndPoint;
    }
}
