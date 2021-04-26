package com.rsystems.noriel.userrecomandations;

public class User {
    private String emailHash;

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailHash='" + emailHash + '\'' +
                '}';
    }
}
