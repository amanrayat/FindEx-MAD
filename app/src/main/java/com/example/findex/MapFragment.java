package com.example.findex;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class MapFragment extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    private TextView building;
    private TextView room;
    private TextView hours;
    private TextView phone;
    private static final int PERMISSION_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        building = findViewById(R.id.buildingLocation);
        room = findViewById(R.id.roomLocation);
        hours = findViewById(R.id.hoursLocation);
        phone = findViewById(R.id.phoneNumber);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String location = getIntent().getStringExtra("location");

        LatLng finalLocation;
        String finalBuilding = "Building: COMING SOON";
        String finalRoom  = "Room: COMING SOON";
        String finalHours  = "Hours: COMING SOON";
        String finalPhone  = "Phone: COMING SOON";

        switch (location){
            case "khoury":
                finalLocation = new LatLng(42.338681, -71.092176);
                mMap.addMarker(new MarkerOptions().position(finalLocation).title("CCIS Building"));
                finalBuilding = "Building: West Village H";
                break;
            case "snell":
                finalLocation = new LatLng(42.338355, -71.088020);
                mMap.addMarker(new MarkerOptions().position(finalLocation).title("Snell Library"));
                finalBuilding = "Building: Snell Library";
                break;
            case "curry":
                finalLocation = new LatLng(42.339343, -71.087591);
                mMap.addMarker(new MarkerOptions().position(finalLocation).title("Curry Student Center"));
                finalBuilding = "Building: Curry Student Center";
                break;
            case "marino":
                finalLocation = new LatLng(42.340494, -71.090333);
                mMap.addMarker(new MarkerOptions().position(finalLocation).title("Marino Recreation Center"));
                finalBuilding = "Building: Marino Recreation Center";
                break;
                default:
                    finalLocation = new LatLng(42.337944, -71.090145);
                    break;
        }
        building.setText(finalBuilding);
        room.setText(finalRoom);
        hours.setText(finalHours);
        phone.setText(finalPhone);


        mMap.setBuildingsEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(finalLocation)
                .zoom(17)
                .bearing(360)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        permissions();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
            System.out.println("Success");
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    public void permissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {


            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_LOCATION);

            }
        }
    }
}
