package br.com.ifspcmp.mappedwallet.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ifspcmp.mappedwallet.data.dao.base.DAO;
import br.com.ifspcmp.mappedwallet.data.dao.contratos.IDAO;
import br.com.ifspcmp.mappedwallet.data.dao.contratos.ILancamentoDAO;
import br.com.ifspcmp.mappedwallet.data.enumerador.TipoLancamento;
import br.com.ifspcmp.mappedwallet.data.model.Lancamento;
import br.com.ifspcmp.mappedwallet.helper.DataHelper;

public class LancamentoDAO extends DAO implements ILancamentoDAO {

    public LancamentoDAO(@Nullable Context context) {
        super(context);
    }

    @Override
    public void insere(Lancamento lancamento) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues dados = pegaDadosLancamento(lancamento);
        sqLiteDatabase.insert("Lancamento", null, dados);
    }

    @Override
    public List<Lancamento> buscaTodos() {
        String sql = "SELECT * FROM Lancamento;";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor c = sqLiteDatabase.rawQuery(sql, null);
        List<Lancamento> lancamentos = new ArrayList<Lancamento>();
        while(c.moveToNext()){
            RetornaLancamentoBanco(c, lancamentos);
        }
        c.close();

        return lancamentos;
    }

    private void RetornaLancamentoBanco(Cursor c, List<Lancamento> lancamentos) {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(c.getLong(c.getColumnIndex("id")));

        lancamento.setDescricao(c.getString(c.getColumnIndex("descricao")));

        lancamento.setDataPagamento(c.getString(c.getColumnIndex("dataPagamento")));

        lancamento.setValor(new BigDecimal(c.getString(c.getColumnIndex("valor"))));

        String strLat = c.getString(c.getColumnIndex("lat"));
        String strLng = c.getString(c.getColumnIndex("lng"));
        double lat = Double.parseDouble(strLat);
        double lng = Double.parseDouble(strLng);
        LatLng latLng = new LatLng(lat, lng);
        lancamento.setLatLngLocal(latLng);

        String strTipoLancamento = c.getString(c.getColumnIndex("tipoLancamento"));
        lancamento.setTipoLancamento(TipoLancamento.fromString(strTipoLancamento));

        lancamentos.add(lancamento);
    }

    @Override
    public List<Lancamento> buscaLancamentoPorMesEAno(int mes, int ano, int maxDayMonth) {

        List<Lancamento> lancamentos = new ArrayList<Lancamento>();
        List<Lancamento> Todoslancamentos = buscaTodos();

        for(Lancamento l: Todoslancamentos) {

            Date date = null;
            try {
                date = DataHelper.ConvertStringEmData(l.getDataPagamento());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int mesLancamento = DataHelper.RetonarMesData(date);
            int anoLancamento = DataHelper.RetonarAnoData(date);

            if(mesLancamento == mes && anoLancamento == ano){
                lancamentos.add(l);
            }
        }

        return lancamentos;
    }

    @Override
    public void deleta(Lancamento lancamento) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] params = {lancamento.getId().toString()};
        sqLiteDatabase.delete("Lancamento", "id = ?", params);

    }

    @Override
    public void altera(Lancamento lancamento) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues dados = pegaDadosLancamento(lancamento);
        String[] params = {lancamento.getId().toString()};
        sqLiteDatabase.update("Lancamento", dados, "id = ?", params);
    }

    @Override
    public Lancamento buscaId(int id) {
        String sql = "SELECT * FROM Lancamento where id = ?";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(sql, new String[] {String.valueOf(id)});

        List<Lancamento> lancamentos = new ArrayList<Lancamento>();
        while(c.moveToNext()){
            RetornaLancamentoBanco(c, lancamentos);
        }
        c.close();

        return lancamentos.get(0);
    }

    @Override
    public Lancamento busca(Lancamento lancamento) {
        return null;
    }

    @Override
    public ContentValues pegaDadosLancamento(Lancamento lancamento) {
        ContentValues dados = new ContentValues();
        dados.put("descricao", lancamento.getDescricao());
        dados.put("dataPagamento", String.valueOf(lancamento.getDataPagamento()));
        dados.put("valor", lancamento.getValor().toString());
        dados.put("lat", lancamento.getLatLngLocal().latitude);
        dados.put("lng", lancamento.getLatLngLocal().longitude);
        dados.put("tipoLancamento", lancamento.getTipoLancamento().toString());
        return dados;
    }
}
