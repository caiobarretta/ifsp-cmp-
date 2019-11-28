package br.com.ifspcmp.mappedwallet.ui.main.entrarcirculo;

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

public class EntrarCirculoFragment extends Fragment {

    private EntrarCirculoViewModel entrarCirculoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        entrarCirculoViewModel =
                ViewModelProviders.of(this).get(EntrarCirculoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_entrarcirculo, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        entrarCirculoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}