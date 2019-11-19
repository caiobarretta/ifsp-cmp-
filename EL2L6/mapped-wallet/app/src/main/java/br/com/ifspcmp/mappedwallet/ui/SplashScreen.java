package br.com.ifspcmp.mappedwallet.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import br.com.ifspcmp.mappedwallet.R;
import br.com.ifspcmp.mappedwallet.globals.Usuario;
import br.com.ifspcmp.mappedwallet.ui.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    private Handler handler;
    private ProgressBar progress;


    //Globals
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //recupera os componentes da tela: barra de progresso e botão
        progress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);
        //instancia um novo Handler para executar a thread
        handler = new Handler();

        executaThread();
    }

    public void executaThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    final int value = i;
                    try {
                        //define 1/10 segundo como o tempo para a barra
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //define o valor para a barra
                            progress.setProgress(value);
                        }
                    });
                }

                usuario = Usuario.getInstance();
                usuario.setFinishLoading(true);
                if(!usuario.getIsIsLogged()){
                    //Se não estiver logado, redireciona para a tela de login
                    Intent login = new Intent(SplashScreen.this, LoginActivity.class);
                    login.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(login);
                    //Intent register = new Intent(SplashScreen.this, RegisterActivity.class);
                    //register.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    //startActivity(register);
                }
                finish();
            }

        };
        //executa a thread
        Thread t = new Thread(runnable);
        t.start();

    }
}
