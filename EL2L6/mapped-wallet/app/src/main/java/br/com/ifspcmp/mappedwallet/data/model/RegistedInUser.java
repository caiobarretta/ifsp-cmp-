package br.com.ifspcmp.mappedwallet.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class RegistedInUser {

    private String userId;
    private String displayName;

    public RegistedInUser(String userId, String displayName) {
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
