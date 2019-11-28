package br.com.ifspcmp.mappedwallet.ui.main.gereneciarcirculo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GereneciarCirculoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GereneciarCirculoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gereneciarcirculo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}