package br.com.ifspcmp.mappedwallet.helper;

public class UrlHelper {

    public static String getUrlAPIUsuarios(){
        return "http://el2l6.gear.host/api/Usuarios";
    }

    public static String getUrlAPIAuth(String login, String senha){
        return "http://el2l6.gear.host/Api/Auth?login="+login+"&senha="+senha;
    }

}
