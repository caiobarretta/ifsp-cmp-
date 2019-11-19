package br.com.ifspcmp.mappedwallet.data.repository;

import br.com.ifspcmp.mappedwallet.data.RegisterDataSource;
import br.com.ifspcmp.mappedwallet.data.Result;
import br.com.ifspcmp.mappedwallet.data.model.RegisteredInUser;

public class RegisterRepository {

    private static volatile RegisterRepository instance;

    private RegisterDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private RegisteredInUser user = null;

    // private constructor : singleton access
    private RegisterRepository(RegisterDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RegisterRepository getInstance(RegisterDataSource dataSource) {
        if (instance == null) {
            instance = new RegisterRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setRegisteredInUser(RegisteredInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<RegisteredInUser> register(String username, String password) {
        // handle login
        Result<RegisteredInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setRegisteredInUser(((Result.Success<RegisteredInUser>) result).getData());
        }
        return result;
    }
}
