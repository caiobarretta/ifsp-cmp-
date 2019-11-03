package br.com.ifspcmp.mappedwallet.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import br.com.ifspcmp.mappedwallet.globals.Usuario;
import br.com.ifspcmp.mappedwallet.R;
import br.com.ifspcmp.mappedwallet.ui.SplashScreen;
import br.com.ifspcmp.mappedwallet.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {


    //Components
    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar barraDeFerramentas;
    private FloatingActionButton botaoAdd;
    private DrawerLayout menuDrawer;
    private NavigationView navigationView;

    //Globals
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Inicia a tela de carregamento
        Intent splashScreen = new Intent(MainActivity.this, SplashScreen.class);
        splashScreen.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(splashScreen);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barraDeFerramentas = findViewById(R.id.toolbar);
        setSupportActionBar(barraDeFerramentas);

        botaoAdd = findViewById(R.id.fab);
        menuDrawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(menuDrawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Verifica se usuário está logado e não está carregando
        usuario = Usuario.getInstance();
        if(!usuario.getIsIsLogged() && usuario.getIsFinishLoading()){
            //Se não estiver logado, redireciona para a tela de login
            Intent login = new Intent(MainActivity.this, LoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(login);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!usuario.getIsIsLogged() && usuario.getIsFinishLoading()){
            //Se não estiver logado, redireciona para a tela de login
            Intent login = new Intent(MainActivity.this, LoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(login);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onBotaoAddClick(View view)
    {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
