package br.com.ifspcmp.mappedwallet.data.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.ifspcmp.mappedwallet.R;
import br.com.ifspcmp.mappedwallet.data.model.Lancamento;

public class LancamentoAdapter extends BaseAdapter {

    private List<Lancamento> lancamentos;
    private Context context;

    public LancamentoAdapter(List<Lancamento> lancamentos, Context context){
        this.lancamentos = lancamentos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.lancamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lancamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.lancamentos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lancamento lancamento = this.lancamentos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if (view == null) view = inflater.inflate(R.layout.list_lancamento, parent, false);

        TextView list_lancamento_data_pagamento = view.findViewById(R.id.list_lancamento_data_pagamento);
        list_lancamento_data_pagamento.setText(lancamento.getDataPagamento());

        TextView list_lancamento_descricao = view.findViewById(R.id.list_lancamento_descricao);
        list_lancamento_descricao.setText(lancamento.getDescricao());

        TextView list_lancamento_valor = view.findViewById(R.id.list_lancamento_valor);
        list_lancamento_valor.setText(NumberFormat.getCurrencyInstance().format(lancamento.getValor()));

        return view;
    }

}
