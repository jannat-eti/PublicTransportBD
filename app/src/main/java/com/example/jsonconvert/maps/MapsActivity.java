package com.example.jsonconvert.maps;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.example.jsonconvert.R;
import com.example.jsonconvert.location.MyLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap, mMap2;
    private Marker marker, marker2;

    MyLocation myLocation = new MyLocation();

    String fromLocation, toLocation;

    LatLng fromLatLng, toLatLng;

    LatLng dest, origin;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        fromLocation = getIntent().getStringExtra("fromLocation");
        toLocation = getIntent().getStringExtra("toLocation");

        fromLatLng = myLocation.getLocationFromAddress(MapsActivity.this, fromLocation);
        toLatLng = myLocation.getLocationFromAddress(MapsActivity.this, toLocation);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        marker = mMap.addMarker(new MarkerOptions().position(fromLatLng)
                .title(fromLocation)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .alpha(1f));
        marker.showInfoWindow();


        mMap.moveCamera(CameraUpdateFactory.newLatLng(fromLatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(fromLatLng, 15.5f));
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);



        marker2 = mMap.addMarker(new MarkerOptions().position(toLatLng)
                .title(toLocation)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .alpha(1f));
        marker2.showInfoWindow();







        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(MapsActivity.this.getResources().getColor(R.color.colorRed))
                .add(
                        new LatLng(fromLatLng.latitude, fromLatLng.longitude),
                        new LatLng(toLatLng.latitude, toLatLng.longitude)));




//        mMap.moveCamera(CameraUpdateFactory.newLatLng(toLatLng));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(toLatLng, 15.0f));
//        mMap.getUiSettings().setCompassEnabled(true);
//        mMap.getUiSettings().setZoomControlsEnabled(true);





    }





















}
