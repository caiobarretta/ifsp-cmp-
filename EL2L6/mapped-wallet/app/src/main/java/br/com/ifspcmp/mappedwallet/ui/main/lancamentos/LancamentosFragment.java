package br.com.ifspcmp.mappedwallet.ui.main.lancamentos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.NumberFormat;
import java.util.List;

import br.com.ifspcmp.mappedwallet.R;
import br.com.ifspcmp.mappedwallet.data.adapter.LancamentoAdapter;
import br.com.ifspcmp.mappedwallet.data.dao.LancamentoDAO;
import br.com.ifspcmp.mappedwallet.data.model.Lancamento;
import br.com.ifspcmp.mappedwallet.helper.DataHelper;

public class LancamentosFragment extends Fragment {

    private int mesAtual = 0;
    private int anoAtual = 0;
    private int maxDayActualMonth = 0;

    private ListView lancamento_lst;
    private TextView lancamento_txt;
    private Button lancamento_btn_add;
    private Button lancamento_btn_back;
    private List<Lancamento> lancamentos;
    private Button lancamento_btn_forward;
    private TextView lancamento_txt_total;
    private LancamentosViewModel lancamentosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lancamentosViewModel =
                ViewModelProviders.of(this).get(LancamentosViewModel.class);

        View root = inflater.inflate(R.layout.fragment_lancamento, container, false);

        lancamento_txt = root.findViewById(R.id.lancamento_txt_mes);

        mesAtual = DataHelper.RetornaMesAtual();
        anoAtual = DataHelper.RetornaAnoAtual();
        maxDayActualMonth = DataHelper.RetornaMaximoDiaMesAtual();

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
                CarregaLancamentos();
                AtualizaLancamentoTxt();
            }
        });

        lancamento_btn_forward = root.findViewById(R.id.lancamento_btn_forward);
        lancamento_btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncrementaZeraMesAtual(+1);
                CarregaLancamentos();
                AtualizaLancamentoTxt();
            }
        });

        lancamento_btn_add = root.findViewById(R.id.lancamento_btn_add);
        lancamento_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LancamentoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        lancamento_lst = root.findViewById(R.id.lancamento_lst);
        lancamento_lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Lancamento lancamento = (Lancamento) lancamento_lst.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), LancamentoActivity.class);
                intent.putExtra("lancamento", lancamento);
                startActivity(intent);
            }
        });
        registerForContextMenu(lancamento_lst);

        lancamento_txt_total = root.findViewById(R.id.lancamento_txt_total);

        CarregaLancamentos();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        CarregaLancamentos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Lancamento lancamento = (Lancamento) lancamento_lst.getItemAtPosition(info.position);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                LancamentoDAO dao = new LancamentoDAO(getContext());
                dao.deleta(lancamento);
                dao.close();

                CarregaLancamentos();
                return false;
            }
        });
    }

    private void CarregaLancamentos() {
        LancamentoDAO dao = new LancamentoDAO(getContext());
        lancamentos = dao.buscaLancamentoPorMesEAno(mesAtual, anoAtual, maxDayActualMonth);
        dao.close();
        lancamento_lst.setAdapter(new LancamentoAdapter(lancamentos, this.getContext()));

        double valorTotal = 0;
        for(Lancamento lancamento: lancamentos){
            valorTotal += lancamento.getValor().doubleValue();
        }
        lancamento_txt_total.setText("Total: " +
                NumberFormat.getCurrencyInstance().format(valorTotal));
    }

    private void IncrementaZeraMesAtual(int incremento) {
        mesAtual += incremento;
        if(mesAtual > 11 ){
            mesAtual = 0;
            anoAtual += 1;
        }
        if(mesAtual < 0){
            mesAtual = 11;
            anoAtual -= 1;
        }

        maxDayActualMonth = DataHelper.RetornaMaximoDiaMes(mesAtual, anoAtual);
    }

    private void AtualizaLancamentoTxt() {
        String mesAtualTexto = DataHelper.GetMesEmPortugues(mesAtual);
        lancamento_txt.setText(mesAtualTexto + "/" + anoAtual);
    }

}