package br.com.ifspcmp.mappedwallet.data.enumerador;

public enum TipoLancamento {
    RECEITA, DESPESA;

    public static TipoLancamento fromInteger(int codigo) {
        switch(codigo) {
            case 0:
                return TipoLancamento.RECEITA;
            case 1:
                return TipoLancamento.DESPESA;
        }
        return null;
    }

    public static TipoLancamento fromString(String str) {
        switch(str) {
            case "RECEITA":
                return TipoLancamento.RECEITA;
            case "DESPESA":
                return TipoLancamento.DESPESA;
        }
        return null;
    }
}
