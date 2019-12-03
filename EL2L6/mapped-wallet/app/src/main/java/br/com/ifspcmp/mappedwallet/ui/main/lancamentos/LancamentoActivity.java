package br.com.ifspcmp.mappedwallet.ui.main.lancamentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import br.com.ifspcmp.mappedwallet.R;
import br.com.ifspcmp.mappedwallet.data.dao.LancamentoDAO;
import br.com.ifspcmp.mappedwallet.data.enumerador.TipoLancamento;
import br.com.ifspcmp.mappedwallet.data.model.Lancamento;
import br.com.ifspcmp.mappedwallet.helper.DataHelper;
import br.com.ifspcmp.mappedwallet.service.GPSTrackerService;

public class LancamentoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private EditText lancamento_txt_datapagamento;
    private Button lancamento_btn_datapickerdialog;
    private EditText lancamento_txt_descricao;
    private EditText lancamento_txt_latitude;
    private EditText lancamento_txt_longitude;
    private EditText lancamento_txt_valor;

    private MapView lancamento_mapview;
    private GoogleMap map;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private Lancamento lancamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);

        lancamento_txt_datapagamento = findViewById(R.id.lancamento_txt_datapagamento);
        lancamento_btn_datapickerdialog = findViewById(R.id.lancamento_btn_datapickerdialog);
        lancamento_txt_descricao = findViewById(R.id.lancamento_txt_descricao);
        lancamento_txt_latitude = findViewById(R.id.lancamento_txt_latitude);
        lancamento_txt_longitude = findViewById(R.id.lancamento_txt_longitude);
        lancamento_txt_valor = findViewById(R.id.lancamento_txt_valor);

        lancamento_mapview = (MapView) findViewById(R.id.lancamento_mapview);
        lancamento_mapview.onCreate(savedInstanceState);
        lancamento_mapview.getMapAsync(this);

        lancamento = new Lancamento();

        Intent intent = getIntent();
        Lancamento lancamentoUpdate = (Lancamento) intent.getSerializableExtra("lancamento");

        if(lancamentoUpdate != null) {
            String msg = "lancamentoUpdate.LatLngLocal: " + lancamentoUpdate.getLatLngLocal().toString();
            Log.e("getView", msg);
            preencheFormulario(lancamentoUpdate);
            this.lancamento.setId(lancamentoUpdate.getId());
        }

    }

    private Lancamento carregaLancamentoForm(){

        lancamento.setDescricao(lancamento_txt_descricao.getText().toString());

        lancamento.setDataPagamento(lancamento_txt_datapagamento.getText().toString());

        String valor = lancamento_txt_valor.getText().toString();

        if (valor.isEmpty()) lancamento.setValor(new BigDecimal("0"));
        else lancamento.setValor(new BigDecimal(valor));

        double lat = Double.parseDouble(lancamento_txt_latitude.getText().toString());
        double lng = Double.parseDouble(lancamento_txt_longitude.getText().toString());
        LatLng latLng = new LatLng(lat, lng);
        lancamento.setLatLngLocal(latLng);

        lancamento.setTipoLancamento(TipoLancamento.RECEITA);

        return lancamento;

    }

    private void  preencheFormulario(Lancamento lancamento){
        lancamento_txt_datapagamento.setText(lancamento.getDataPagamento());
        lancamento_txt_descricao.setText(lancamento.getDescricao());

        if(lancamento.getLatLngLocal() != null){
            lancamento_txt_latitude.setText(String.valueOf(lancamento.getLatLngLocal().latitude));
            lancamento_txt_longitude.setText(String.valueOf(lancamento.getLatLngLocal().longitude));
        }

        lancamento_txt_valor.setText(lancamento.getValor().toString());
    }

    public void onClickLancamentoBtnDataPickerDialog(View view){

        calendar = Calendar.getInstance();

        int day  = calendar.get(Calendar.DAY_OF_MONTH);
        int month  = calendar.get(Calendar.MONTH);
        int year  = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dYear, int dMonth, int dDay) {
                //yyyy/MM/dd
                lancamento_txt_datapagamento.setText(dYear +"/"+ (dMonth + 1) +"/"+ dDay);
            }
        }, year, month, day);

        datePickerDialog.show();

    }

    private boolean lancamentoValido(Lancamento lancamento){

        if(lancamento.getDescricao().isEmpty()) return false;

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lancamento, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_lancamento_ok:
                lancamento = carregaLancamentoForm();

                if(!lancamentoValido(lancamento)){
                    Toast.makeText(LancamentoActivity.this, "Algum parametro está inválido ou não preenchido",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                LancamentoDAO dao = new LancamentoDAO(this);

                if(lancamento.getId() != null)
                    dao.altera(lancamento);
                else
                    dao.insere(lancamento);

                dao.close();

                Toast.makeText(LancamentoActivity.this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);

        GPSTrackerService gpsTrackerService = new GPSTrackerService(this);
        LatLng latLng;

        if(lancamento_txt_latitude.getText().toString().isEmpty() && lancamento_txt_longitude.getText().toString().isEmpty()) {
            latLng = new LatLng(gpsTrackerService.getLatitude(), gpsTrackerService.getLongitude());
            lancamento_txt_latitude.setText(String.valueOf(latLng.latitude));
            lancamento_txt_longitude.setText(String.valueOf(latLng.longitude));
        }
        else{
            double lat = Double.parseDouble(lancamento_txt_latitude.getText().toString());
            double lng = Double.parseDouble(lancamento_txt_longitude.getText().toString());
            latLng = new LatLng(lat, lng);
        }

        map.addMarker(new MarkerOptions().position(latLng));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, R.integer.zoom_map));

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                lancamento_txt_latitude.setText(String.valueOf(latLng.latitude));
                lancamento_txt_longitude.setText(String.valueOf(latLng.longitude));

                map.clear();

                map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                map.addMarker(markerOptions);
            }
        });
    }

    @Override
    public void onResume() {
        lancamento_mapview.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lancamento_mapview.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        lancamento_mapview.onLowMemory();
    }
}
