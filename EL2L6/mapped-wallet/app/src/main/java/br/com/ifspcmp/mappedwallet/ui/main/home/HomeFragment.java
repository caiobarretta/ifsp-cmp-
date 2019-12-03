package br.com.ifspcmp.mappedwallet.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.ifspcmp.mappedwallet.R;
import br.com.ifspcmp.mappedwallet.service.GPSTrackerService;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel homeViewModel;

    private MapView home_mapview;
    private GoogleMap map;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        home_mapview = (MapView) root.findViewById(R.id.home_mapview);
        home_mapview.onCreate(savedInstanceState);
        home_mapview.getMapAsync(this);

        return root;
    }

    @Override
    public void onResume() {
        home_mapview.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        home_mapview.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        home_mapview.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);

        GPSTrackerService gpsTrackerService = new GPSTrackerService(this.getContext());
        LatLng latLng = new LatLng(gpsTrackerService.getLatitude(), gpsTrackerService.getLongitude());

        map.addMarker(new MarkerOptions().position(latLng));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, R.integer.zoom_map));
    }
}