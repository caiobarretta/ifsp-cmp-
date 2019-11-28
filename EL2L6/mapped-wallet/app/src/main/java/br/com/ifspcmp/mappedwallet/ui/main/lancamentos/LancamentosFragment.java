package br.com.ifspcmp.mappedwallet.ui.main.lancamentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.com.ifspcmp.mappedwallet.R;

public class LancamentosFragment extends Fragment {

    private LancamentosViewModel lancamentosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lancamentosViewModel =
                ViewModelProviders.of(this).get(LancamentosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_relatorios, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        lancamentosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}