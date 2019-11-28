package br.com.ifspcmp.mappedwallet.ui.main.lancamentos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LancamentosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LancamentosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}