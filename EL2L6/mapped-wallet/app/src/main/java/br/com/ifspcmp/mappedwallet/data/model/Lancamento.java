package br.com.ifspcmp.mappedwallet.data.model;

import com.google.android.gms.maps.model.LatLng;

import java.math.BigDecimal;
import java.util.Date;

import br.com.ifspcmp.mappedwallet.data.enumerador.TipoLancamento;

public class Lancamento {
    private Long id;
    private String descricao;
    private Date dataPagamento;
    private BigDecimal valor;
    private LatLng latLngLocal;
    private TipoLancamento tipoLancamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LatLng getLatLngLocal() {
        return latLngLocal;
    }

    public void setLatLngLocal(LatLng latLngLocal) {
        this.latLngLocal = latLngLocal;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }
}
