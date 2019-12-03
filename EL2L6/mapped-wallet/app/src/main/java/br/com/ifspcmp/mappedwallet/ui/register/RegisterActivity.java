package br.com.ifspcmp.mappedwallet.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import br.com.ifspcmp.mappedwallet.R;
import br.com.ifspcmp.mappedwallet.data.Result;
import br.com.ifspcmp.mappedwallet.data.model.LoggedInUser;
import br.com.ifspcmp.mappedwallet.data.model.web.Usuario;
import br.com.ifspcmp.mappedwallet.helper.UrlHelper;
import br.com.ifspcmp.mappedwallet.service.web.ServicePostReqAsyncTask;
import br.com.ifspcmp.mappedwallet.ui.SplashScreen;
import br.com.ifspcmp.mappedwallet.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_username;
    private EditText register_password;
    private EditText register_confirm_password;
    private Button register_btnregister;
    private ProgressBar register_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_username = findViewById(R.id.register_username);
        register_password = findViewById(R.id.register_password);
        register_confirm_password = findViewById(R.id.register_confirm_password);
        register_btnregister = findViewById(R.id.register_btnregister);
        register_loading = findViewById(R.id.register_loading);

    }

    public void onClickRegister(View view){
        String user = register_username.getText().toString();
        String pass = register_password.getText().toString();
        String confirmPass = register_confirm_password.getText().toString();

        if(user.isEmpty()){
            Toast.makeText(this, "Usuário invalido", Toast.LENGTH_LONG).show();
            return;
        }
        if(pass.isEmpty()){
            Toast.makeText(this, "Senhas invalida", Toast.LENGTH_LONG).show();
            return;
        }

        if(pass.length() < 5){
            Toast.makeText(this, "Senhas invalida, a senha não pode ser menor que 5 caracteres",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(!pass.equals(confirmPass)){
            Toast.makeText(this, "As senhas não correspondem", Toast.LENGTH_LONG).show();
            return;
        }

        Usuario usuario = new Usuario();
        usuario.usuario_email = register_username.getText().toString();
        usuario.usuario_senha = register_password.getText().toString();

        String usuarioJSON = new Gson().toJson(usuario);

        String retorno = null;
        try {
            retorno = new ServicePostReqAsyncTask().execute(UrlHelper.getUrlAPIUsuarios(), usuarioJSON).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocorreu um erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocorreu um erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        if(retorno != null) {
            Usuario usuarioCriado = new Gson().fromJson(retorno, Usuario.class);
            Toast.makeText(this, "Cadastro concluido com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(RegisterActivity.this, "Não foi possivel completar a ação.", Toast.LENGTH_LONG).show();
        }
    }
}
