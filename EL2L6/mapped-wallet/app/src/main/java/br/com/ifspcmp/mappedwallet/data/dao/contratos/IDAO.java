package br.com.ifspcmp.mappedwallet.data.dao.contratos;

import java.util.List;

public interface IDAO<T> {

    void insere(T t);

    List<T> buscaTodos();

    void deleta(T t);

    void altera(T t);

    T buscaId(int id);

    T busca(T t);

}
