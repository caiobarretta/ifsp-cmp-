package br.com.ifspcmp.mappedwallet.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.ifspcmp.mappedwallet.data.model.LoggedInUser;
import br.com.ifspcmp.mappedwallet.data.model.web.Usuario;
import br.com.ifspcmp.mappedwallet.helper.UrlHelper;
import br.com.ifspcmp.mappedwallet.service.web.ServiceGetReqAsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication


            String retorno = new ServiceGetReqAsyncTask().execute(UrlHelper.getUrlAPIAuth(username, password)).get();

            if(retorno != null) {

                Usuario usuario = new Gson().fromJson(retorno, Usuario.class);

                Log.e("usuario", usuario.usuario_email);

                LoggedInUser user =
                        new LoggedInUser(
                                String.valueOf(usuario.id_usuario),
                                usuario.usuario_email);

                return new Result.Success<>(user);
            }
            return new Result.Error( new Exception("Usuário ou senha não encontrado"));

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
