package br.com.ifspcmp.mappedwallet.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataHelper {

    public static String GetMesEmPortugues(){
        int mes = DataHelper.RetornaMesAtual();
        return ConvertMesEmStringPortugues(mes);
    }

    public static String GetMesEmPortugues(int mes){
        return ConvertMesEmStringPortugues(mes);
    }

    private static String ConvertMesEmStringPortugues(int mes) {
        String dataAtual = "";
        switch (mes){
            case 0:
                dataAtual = "Janeiro";
                break;
            case 1:
                dataAtual = "Fevereiro";
                break;
            case 2:
                dataAtual = "Março";
                break;
            case 3:
                dataAtual = "Abril";
                break;
            case 4:
                dataAtual = "Maio";
                break;
            case 5:
                dataAtual = "Junho";
                break;
            case 6:
                dataAtual = "Julho";
                break;
            case 7:
                dataAtual = "Agosto";
                break;
            case 8:
                dataAtual = "Setembro";
                break;
            case 9:
                dataAtual = "Outubro";
                break;
            case 10:
                dataAtual = "Novembro";
                break;
            case 11:
                dataAtual = "Dezembro";
                break;

        }

        return dataAtual;
    }

    public static int RetornaMesAtual(){
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        return dataCal.get(Calendar.MONTH);
    }
}
