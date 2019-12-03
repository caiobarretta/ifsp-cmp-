package br.com.ifspcmp.mappedwallet.data.dao.contratos;

import android.content.ContentValues;

import java.util.List;

import br.com.ifspcmp.mappedwallet.data.model.Lancamento;

public interface ILancamentoDAO extends IDAO<Lancamento> {

    ContentValues pegaDadosLancamento(Lancamento lancamento);

    List<Lancamento> buscaLancamentoPorMesEAno(int mes, int ano, int maxDayMonth);
}
