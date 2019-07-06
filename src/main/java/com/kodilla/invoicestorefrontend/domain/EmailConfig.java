package com.kodilla.invoicestorefrontend.domain;

public class EmailConfig {
    private Long emailConfigId;
    private String emailAddress;
    private String smtpServer;
    private int smtpPort;
    private String username;
    private String password;
    private boolean isAuthReq;
    private EncryptionType encryptionType;
    private Long userId;

    public EmailConfig() {
    }

    public EmailConfig(Long emailConfigId, String emailAddress, String smtpServer, int smtpPort, String username, String password, boolean isAuthReq, EncryptionType encryptionType, Long userId) {
        this.emailConfigId = emailConfigId;
        this.emailAddress = emailAddress;
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
        this.isAuthReq = isAuthReq;
        this.encryptionType = encryptionType;
        this.userId = userId;
    }

    public Long getEmailConfigId() {
        return emailConfigId;
    }

    public void setEmailConfigId(Long emailConfigId) {
        this.emailConfigId = emailConfigId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthReq() {
        return isAuthReq;
    }

    public void setAuthReq(boolean authReq) {
        isAuthReq = authReq;
    }

    public EncryptionType getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailConfig that = (EmailConfig) o;

        if (smtpPort != that.smtpPort) return false;
        if (isAuthReq != that.isAuthReq) return false;
        if (emailConfigId != null ? !emailConfigId.equals(that.emailConfigId) : that.emailConfigId != null)
            return false;
        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;
        if (smtpServer != null ? !smtpServer.equals(that.smtpServer) : that.smtpServer != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (encryptionType != that.encryptionType) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;
    }

    @Override
    public int hashCode() {
        int result = emailConfigId != null ? emailConfigId.hashCode() : 0;
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (smtpServer != null ? smtpServer.hashCode() : 0);
        result = 31 * result + smtpPort;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isAuthReq ? 1 : 0);
        result = 31 * result + (encryptionType != null ? encryptionType.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
