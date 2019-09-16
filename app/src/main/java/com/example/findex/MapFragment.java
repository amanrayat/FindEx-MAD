package com.example.findex;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
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

public class MapFragment extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

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
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        building = findViewById(R.id.buildingLocation);
        room = findViewById(R.id.roomLocation);
        hours = findViewById(R.id.hoursLocation);
        phone = findViewById(R.id.phoneNumber);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        String location = getIntent().getStringExtra("location");
        LatLng finalLocation;
        String finalBuilding = "Building: COMING SOON";
        String finalRoom  = "Room: COMING SOON";
        String finalHours  = "Hours: COMING SOON";
        String finalPhone  = "Phone: COMING SOON";

        assert location != null;
        switch (location){
            case "NUPD":
                finalLocation = new LatLng(42.337680, -71.085168);
                googleMap.addMarker(new MarkerOptions().position(finalLocation).title("NUPD"));
                finalBuilding = "Building: NUPD";
                finalRoom  = "";
                finalHours  = "Hours: 8:00am - 4:00pm";
                finalPhone  = "Phone: 617-373-2121";
                break;
            case "snell":
                finalLocation = new LatLng(42.338355, -71.088020);
                googleMap.addMarker(new MarkerOptions().position(finalLocation).title("Snell Library"));
                finalBuilding = "Building: Snell Library";
                finalRoom  = "Help and Information Desk";
                finalHours  = "Hours: Open 24HR";
                finalPhone  = "Phone: 617-373-8778";
                break;
            case "curry":
                finalLocation = new LatLng(42.339343, -71.087591);
                googleMap.addMarker(new MarkerOptions().position(finalLocation).title("Curry Student Center"));
                finalBuilding = "Building: Curry Student Center";
                finalRoom  = "";
                finalHours  = "Hours: 7:00AM - 12:00AM";
                finalPhone  = "";
                break;
            case "marino":
                finalLocation = new LatLng(42.340494, -71.090333);
                googleMap.addMarker(new MarkerOptions().position(finalLocation).title("Marino Recreation Center"));
                finalBuilding = "Building: Marino Recreation Center";
                finalRoom  = "First Floor";
                finalHours  = "Hours: 5:00am - 12:00pm";
                finalPhone  = "Phone: 617-373-4433";
                break;
                default:
                    finalLocation = new LatLng(42.337944, -71.090145);
                    break;
        }
        building.setText(finalBuilding);
        room.setText(finalRoom);
        hours.setText(finalHours);
        phone.setText(finalPhone);


        googleMap.setBuildingsEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(finalLocation)
                .zoom(17)
                .bearing(360)
                .tilt(30)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        permissions();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            googleMap.setMyLocationEnabled(true);
            googleMap.setOnMyLocationButtonClickListener(this);
            googleMap.setOnMyLocationClickListener(this);
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
