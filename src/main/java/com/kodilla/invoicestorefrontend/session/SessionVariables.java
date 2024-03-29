package com.kodilla.invoicestorefrontend.session;

import com.kodilla.invoicestorefrontend.domain.User;

public class SessionVariables {
    private static SessionVariables sessionVariables;
    private User currentUser;

    private SessionVariables() {
        currentUser = new User();
    }

    public static SessionVariables getInstance() {
        if (sessionVariables == null) {
            sessionVariables = new SessionVariables();
        }
        return sessionVariables;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
