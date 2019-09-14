package com.antailbaxt3r.kachranikaal;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private static int MY_REQUEST_INT = 177;
    private static int REQUEST_LOCATION_PERMISSION = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private DatabaseReference binReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binReference = FirebaseDatabase.getInstance().getReference().child("bins");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
            return;
        }else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
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

        binReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()){
                    Bin dustbin = shot.getValue(Bin.class);
                    LatLng latLng = new LatLng(dustbin.getLatitude(), dustbin.getLongitude());
                    switch(dustbin.getType()){

                        case "R":
                            if (dustbin.isFull()){
                            mMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title("Recyclable Waste Bin")
                                    .snippet("Full")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_recyclable)));
                            }else{
                                mMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title("Recyclable Waste Bin")
                                        .snippet("Not Full")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_recyclable)));
                            }
                            break;
                        case "NR":
                            if (dustbin.isFull()){
                                mMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title("Non-Recyclable Waste Bin")
                                        .snippet("Full")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_blue)));
                            }else{
                                mMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title("Non-Recyclable Waste Bin")
                                        .snippet("Not Full")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_blue)));
                            }
                            break;
                        case "G":
                            if (dustbin.isFull()){
                                mMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title("General Bin")
                                        .snippet("Full")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon)));
                            }else{
                                mMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title("General Bin")
                                        .snippet("Not Full")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon)));
                            }
                            break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        LatLng bin1 = new LatLng(15.391281, 73.877584);
//        LatLng bin2 = new LatLng (15.390647, 73.87683);
//        LatLng bin3 = new LatLng(15.390556, 73.878625);
//        mMap.addMarker(new MarkerOptions().position(bin2).title("Non-Recycleable Waste Bin")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_blue));
//        mMap.addMarker(new MarkerOptions().position(bin3).title("General Bin")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bin1, 16f));
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
            return;
        }else {
            mMap.setMyLocationEnabled(true);
        }

//        Location currentLocation = mMap.getMyLocation();
//        Location currentLocation = locationManager.getLastKnownLocation(locationManager
//                .getBestProvider(criteria, false));
//        LatLng here = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(here).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if(EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
        else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng here = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(here).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
