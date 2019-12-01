package br.com.ifspcmp.mappedwallet.ui.main.lancamentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.model.LatLng;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ifspcmp.mappedwallet.R;
import br.com.ifspcmp.mappedwallet.data.adapter.LancamentoAdapter;
import br.com.ifspcmp.mappedwallet.data.enumerador.TipoLancamento;
import br.com.ifspcmp.mappedwallet.data.model.Lancamento;
import br.com.ifspcmp.mappedwallet.helper.DataHelper;

public class LancamentosFragment extends Fragment {

    private int mesAtual = 0;
    private TextView lancamento_txt;
    private Button lancamento_btn_back;
    private List<Lancamento> lancamentos;
    private Button lancamento_btn_forward;
    private TextView lancamento_txt_total;
    private LancamentosViewModel lancamentosViewModel;
    private ListView lancamento_lst;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lancamentosViewModel =
                ViewModelProviders.of(this).get(LancamentosViewModel.class);

        View root = inflater.inflate(R.layout.fragment_lancamento, container, false);

        lancamento_txt = root.findViewById(R.id.lancamento_txt_mes);

        mesAtual = DataHelper.RetornaMesAtual();

        lancamentosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                lancamento_txt.setText(s);
            }
        });


        lancamento_btn_back = root.findViewById(R.id.lancamento_btn_back);
        lancamento_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncrementaZeraMesAtual(-1);
                AtualizaLancamentoTxt();
            }
        });

        lancamento_btn_forward = root.findViewById(R.id.lancamento_btn_forward);
        lancamento_btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncrementaZeraMesAtual(+1);
                AtualizaLancamentoTxt();
            }
        });

        lancamento_lst = root.findViewById(R.id.lancamento_lst);

        lancamento_txt_total = root.findViewById(R.id.lancamento_txt_total);

        lancamentos = new ArrayList<Lancamento>();
        Lancamento l = new Lancamento();
        l.setId((long)1);
        l.setDataPagamento(new Date());
        l.setDescricao("Teste");
        l.setLatLngLocal(new LatLng(0, 0));
        l.setTipoLancamento(TipoLancamento.RECEITA);
        l.setValor(new BigDecimal("1000"));
        lancamentos.add(l);

        lancamento_lst.setAdapter(new LancamentoAdapter(lancamentos, this.getContext()));
        AtualizaTotal();

        return root;
    }

    private void IncrementaZeraMesAtual(int incremento) {
        mesAtual += incremento;
        if(mesAtual > 11 ) mesAtual = 0;
        if(mesAtual < 0) mesAtual = 11;
    }

    private void AtualizaLancamentoTxt() {
        String mesAtualTexto = DataHelper.GetMesEmPortugues(mesAtual);
        lancamento_txt.setText(mesAtualTexto);
    }

    private void AtualizaTotal(){
        BigDecimal valorTotal = new BigDecimal("0");
        for(Lancamento lancamento: lancamentos){
            BigDecimal valor = lancamento.getValor();
            valorTotal = valorTotal.add(valor);
        }
        lancamento_txt_total.setText("Total: " +
                NumberFormat.getCurrencyInstance().format(valorTotal));
    }

}