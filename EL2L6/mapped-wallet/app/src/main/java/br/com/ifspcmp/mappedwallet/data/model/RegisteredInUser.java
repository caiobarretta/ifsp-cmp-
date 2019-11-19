package br.com.ifspcmp.mappedwallet.data.model;

public class RegisteredInUser {
    private String userId;
    private String displayName;

    public RegisteredInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
