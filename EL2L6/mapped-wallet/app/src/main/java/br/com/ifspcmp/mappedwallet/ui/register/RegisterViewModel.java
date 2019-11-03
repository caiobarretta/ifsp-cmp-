package br.com.ifspcmp.mappedwallet.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import br.com.ifspcmp.mappedwallet.data.RegisterRepository;
import br.com.ifspcmp.mappedwallet.data.model.RegistedInUser;
import br.com.ifspcmp.mappedwallet.data.Result;
import br.com.ifspcmp.mappedwallet.R;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private RegisterRepository registerRepository;

    RegisterViewModel(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    LiveData<RegisterResult> getRegisterResult() {
        return registerResult;
    }

    public void register(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<RegistedInUser> result = registerRepository.register(username, password);

        if (result instanceof Result.Success) {
            RegistedInUser data = ((Result.Success<RegistedInUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegistredInUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.register_failed));
        }
    }

    public void registerDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
