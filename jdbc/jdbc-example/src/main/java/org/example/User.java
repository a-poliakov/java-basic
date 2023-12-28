package org.example;

import java.util.Set;

public class User {
    private final String login;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", roles=" + roles +
                ", password='" + password + '\'' +
                '}';
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private Set<Role> roles;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }



}
