package br.com.ifspcmp.mappedwallet.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static  int RetonarMesData(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static  int RetonarAnoData(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int RetornaMaximoDiaMesAtual(){
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        return dataCal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int RetornaMaximoDiaMes(int mes, int ano){
        Date data = new Date(ano, mes, 1);
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        return dataCal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int RetornaAnoAtual(){
        Date data = new Date();
        GregorianCalendar dataCal = new GregorianCalendar();
        dataCal.setTime(data);
        return dataCal.get(Calendar.YEAR);
    }

    public static Date ConvertStringEmData(String strData) throws ParseException {
        return new SimpleDateFormat("yyyy/MM/dd").parse(strData);
    }
}
