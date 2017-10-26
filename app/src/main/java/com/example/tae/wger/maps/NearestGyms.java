package com.example.tae.wger.maps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.tae.wger.R;
import com.example.tae.wger.model.GymMapModel;
import com.example.tae.wger.network.AppDataManager;
import com.example.tae.wger.ui.gyms.GymPresenter;
import com.example.tae.wger.ui.gyms.IGymMvpView;
import com.example.tae.wger.ui.utils.rx.AppSchedulerProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class NearestGyms extends FragmentActivity implements OnMapReadyCallback,IGymMvpView {
    GymPresenter<IGymMvpView> gymMvpViewGymPresenter;
   GoogleMap mMap;
    Context mContext;
    GPSTracker gps;
    double latitude ;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearest_gyms);
        mContext = this;
        gymMvpViewGymPresenter = new GymPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        gymMvpViewGymPresenter.onAttach(this);

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NearestGyms.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            Toast.makeText(mContext,"You need have granted permission",Toast.LENGTH_SHORT).show();
            gps = new GPSTracker(mContext, NearestGyms.this);

            // Check if GPS enabled
            if (gps.canGetLocation()) {

              latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                gymMvpViewGymPresenter.onViewPrepared(""+latitude+","+longitude,"gym","5000","AIzaSyAe0cNvNUZBPs0oR17aXp-8sZdkBkSf5Ww");
                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gps.showSettingsAlert();
            }
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location= new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions()
                .position(location).title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,11));




    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the

                    // contacts-related task you need to do.

                    gps = new GPSTracker(mContext, NearestGyms.this);

                    // Check if GPS enabled
                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(mContext, "You need to grant permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public void onFetchDataCompleted(GymMapModel gymMapModel) {
        Log.i("onfetch","completedffffffffffffff");
        List<GymMapModel.Result> gyms=gymMapModel.getResults();

        for (int i = 0; i <gyms.size(); i++)
        {

            GymMapModel.Location loc=gyms.get(i).getGeometry().getLocation();
            LatLng location= new LatLng(loc.getLat(),loc.getLng());
            mMap.addMarker(new MarkerOptions()
                    .position(location).title(gyms.get(i).getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {
     Log.i("errorrrrrrrrrrrrrrrrr",message);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }
}
