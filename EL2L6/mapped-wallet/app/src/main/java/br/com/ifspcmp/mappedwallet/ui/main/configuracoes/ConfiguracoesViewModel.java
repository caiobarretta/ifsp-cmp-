package br.com.ifspcmp.mappedwallet.ui.main.configuracoes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfiguracoesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConfiguracoesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}