package br.com.ifspcmp.mappedwallet.ui.main.entrarcirculo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntrarCirculoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EntrarCirculoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}