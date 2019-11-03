package br.com.ifspcmp.mappedwallet.globals;

public class Usuario {

    private static Usuario instance;
    private static boolean isLogged = false;
    private static  boolean finishLoading = false;

    public static Usuario getInstance() {
        if (instance == null)
            instance = new Usuario();
        return instance;
    }

    private Usuario() { }

    public static boolean getIsIsLogged() {
        return isLogged;
    }

    public static void setIsLogged(boolean isLogged) {
        isLogged = isLogged;
    }

    public static boolean getIsFinishLoading() {
        return finishLoading;
    }

    public static void setFinishLoading(boolean finishLoading) {
        finishLoading = finishLoading;
    }
}
