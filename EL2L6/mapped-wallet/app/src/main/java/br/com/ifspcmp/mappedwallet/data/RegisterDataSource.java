package br.com.ifspcmp.mappedwallet.data;

import java.io.IOException;

import br.com.ifspcmp.mappedwallet.data.model.RegisteredInUser;

public class RegisterDataSource {

    public Result<RegisteredInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            RegisteredInUser fakeUser =
                    new RegisteredInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
