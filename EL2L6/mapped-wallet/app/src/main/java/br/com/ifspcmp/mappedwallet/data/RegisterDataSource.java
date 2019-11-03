package br.com.ifspcmp.mappedwallet.data;

import br.com.ifspcmp.mappedwallet.data.model.RegistedInUser;
import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class RegisterDataSource {

    public Result<RegistedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            RegistedInUser fakeUser =
                    new RegistedInUser(
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
