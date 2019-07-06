package com.kodilla.invoicestorefrontend.domain;

public class User {
    private Long userId;
    private String login;
    private String taxId;
    private String firstname;
    private String lastname;
    private Long emailConfigId;

    public User() {
    }

    public User(Long userId, String login, String taxId, String firstname, String lastname, Long emailConfigId) {
        this.userId = userId;
        this.login = login;
        this.taxId = taxId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailConfigId = emailConfigId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getEmailConfigId() {
        return emailConfigId;
    }

    public void setEmailConfigId(Long emailConfigId) {
        this.emailConfigId = emailConfigId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (taxId != null ? !taxId.equals(user.taxId) : user.taxId != null) return false;
        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        return emailConfigId != null ? emailConfigId.equals(user.emailConfigId) : user.emailConfigId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (taxId != null ? taxId.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (emailConfigId != null ? emailConfigId.hashCode() : 0);
        return result;
    }
}
