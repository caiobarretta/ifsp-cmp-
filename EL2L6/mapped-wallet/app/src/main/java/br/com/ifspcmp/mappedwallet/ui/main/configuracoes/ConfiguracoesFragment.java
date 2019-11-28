package br.com.ifspcmp.mappedwallet.ui.main.configuracoes;

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

public class ConfiguracoesFragment extends Fragment {

    private ConfiguracoesViewModel configuracoesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configuracoesViewModel =
                ViewModelProviders.of(this).get(ConfiguracoesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_configuracoes, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        configuracoesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}