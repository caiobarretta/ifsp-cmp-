package br.com.ifspcmp.mappedwallet.data.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.ifspcmp.mappedwallet.data.enumerador.TipoLancamento;

public class Lancamento implements Serializable {

    private Long id;
    private String descricao;
    private String dataPagamento;
    private BigDecimal valor;

    private double lat;
    private double lng;
    private transient LatLng latLngLocal;

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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LatLng getLatLngLocal() {
        if(latLngLocal == null)
            return new LatLng(this.lat, this.lng);
        else
            return latLngLocal;
    }

    public void setLatLngLocal(LatLng latLngLocal) {
        this.lat = latLngLocal.latitude;
        this.lng = latLngLocal.longitude;
        this.latLngLocal = latLngLocal;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }
}
