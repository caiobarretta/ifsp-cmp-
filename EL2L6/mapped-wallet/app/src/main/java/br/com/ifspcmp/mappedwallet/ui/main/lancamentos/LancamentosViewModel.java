package br.com.ifspcmp.mappedwallet.ui.main.lancamentos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.ifspcmp.mappedwallet.helper.DataHelper;

public class LancamentosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LancamentosViewModel() {
        mText = new MutableLiveData<>();

        String mesAtual = DataHelper.GetMesEmPortugues();
        mText.setValue(mesAtual);
    }

    public LiveData<String> getText() {
        return mText;
    }

}