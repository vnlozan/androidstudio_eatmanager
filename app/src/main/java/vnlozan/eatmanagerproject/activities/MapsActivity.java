package vnlozan.eatmanagerproject.activities;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import vnlozan.eatmanagerproject.R;
import vnlozan.eatmanagerproject.rstrntInfo.RstrntCoordinates;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<RstrntCoordinates> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("LIST");
        list = (ArrayList<RstrntCoordinates>) args.getSerializable("ARRAYLIST");

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
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        // Add a marker in Sydney and move the camera
        LatLng city = new LatLng(46.9992217876332, 28.866405487060547);                                     //Chisinau City
        for(RstrntCoordinates object:list)
        {
            System.out.println("_________________");
            System.out.println(object.getCoordX());
            System.out.println(object.getCoordY());
            System.out.println("_________________");
            mMap.addMarker(new MarkerOptions().position(new LatLng(object.getCoordX(),object.getCoordY())).title(object.getName()));    //Markers
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                city, 12));
    }
}
