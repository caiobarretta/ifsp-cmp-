package br.com.ifspcmp.prjconsomewebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void consumir(View view){
        TextView txtEnderecoCEP = (TextView) findViewById(R.id.edtTxtEnderecoCEP);
        BuscarCEP downloadDados = new BuscarCEP(txtEnderecoCEP, txtEnderecoCEP.toString());

        downloadDados.cep = ((TextView) findViewById(R.id.edtTxtCEP)).getText().toString();
        downloadDados.execute();
    }
}
