package com.kodilla.invoicestorefrontend.service;

import com.kodilla.invoicestorefrontend.client.EmailConfigClient;
import com.kodilla.invoicestorefrontend.domain.EmailConfig;

import java.util.HashSet;
import java.util.Set;

public class EmailConfigService {

    private Set<EmailConfig> emailConfigs;
    private static EmailConfigService emailConfigService;
    private EmailConfigClient emailConfigClient = EmailConfigClient.getInstance();

    private EmailConfigService(){
        this.emailConfigs = new HashSet<>();
    }

    public static EmailConfigService getInstance(){
        if(emailConfigService == null) {
            emailConfigService = new EmailConfigService();
        }
        return emailConfigService;
    }

    public Set<EmailConfig> getEmailConfigs() {
        return emailConfigClient.getEmailConfigs();
    }

    public EmailConfig getEmailConfig(Long id) {
        return emailConfigClient.getEmailConfig(id);
    }


}
