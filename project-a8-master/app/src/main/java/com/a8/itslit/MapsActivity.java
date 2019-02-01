package com.a8.itslit;
//TEST URL https://stackoverflow.com/questions/29924564/using-espresso-to-unit-test-google-maps

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap mMap;
    private final ArrayList<Location> locationList = new ArrayList<>();
    private LocationManager locationManager;

    private static boolean locationButtonClicked = false, markerClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        mapFragment.getMapAsync(this);
//        int PLACE_PICKER_REQUEST = 1;
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//
//        try {
//            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException e) {
//            e.printStackTrace();
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }

        Button returnB = findViewById(R.id.return_btn);
        returnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, ListActivity.class));
            }
        });


        //UNCOMMENT WHEN PARSER HAS BEEN ADDED
//        try {
//            Parser parser = new Parser(this);
//            parser.parseExcel();
//            locationList = parser.getLocationList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        buildLocationList();
//        locationList = Parser.getHardLocaitonList();

//        Log.d("000000000", locationList.toString());

//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
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
        double currentLocationLat, currentLocationLng;
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                googleSearch(getLocationFromList(marker.getTitle()));
            }
        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                locationButtonClicked = true;
                return false;
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                markerClicked = true;
                return false;
            }
        });

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("000000", "NO PERMS");
        }
        android.location.Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        if (location != null) {
            currentLocationLng = location.getLongitude();
            currentLocationLat = location.getLatitude();
            LatLng currentLatLng = new LatLng(currentLocationLat, currentLocationLng);
            Log.d("000000000000000000", currentLatLng.toString());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));

        } else Log.d("00000000000000000", "Last location is NULL");
        updateCurrentLocation();

//=====================================================================================
//  SET ZOOM LEVEL TO 15 OVER THE CURRENT LOCATION WHEN THE MAP OPENS
//=====================================================================================
        markFromList(locationList);
    }

    private void markFromList(ArrayList<Location> listIn) {
        LatLng point;


        for (Location l : listIn) {
//            coords = MapUtils.getInstance().getCoordinates(l.getAddress());
//            Log.d("00000000", coords.toString());
//            point = new LatLng(coords.get("lat"), coords.get("lon"));
            point = new LatLng(l.getLat(), l.getLng());
            mMap.addMarker(new MarkerOptions().position(point).title(l.getName()).snippet(l.getDiscription()));
//            if(l.isOpen())
//            {
//                mMap.addMarker(new MarkerOptions().position(point).title(l.getName()).snippet(l.getDiscription()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//            }
//            else
//            {
//                mMap.addMarker(new MarkerOptions().position(point).title(l.getName()).snippet(l.getDiscription()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//            }
//            switch (l.getBusyness())
//            {
//                case 0:
//                    mMap.addMarker(new MarkerOptions().position(point).title(l.getName()).snippet(l.getDiscription()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
//                    break;
//                case 1:
//                case 2:
//                case 3:
//                    mMap.addMarker(new MarkerOptions().position(point).title(l.getName()).snippet(l.getDiscription()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//                    break;
//                case 4:
//                case 5:
//                    mMap.addMarker(new MarkerOptions().position(point).title(l.getName()).snippet(l.getDiscription()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
//                    break;
//                case 6:
//                case 7:
//                    mMap.addMarker(new MarkerOptions().position(point).title(l.getName()).snippet(l.getDiscription()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
//                    break;
//                case 8:
//                case 9:
//                case 10:
//                    mMap.addMarker(new MarkerOptions().position(point).title(l.getName()).snippet(l.getDiscription()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//                    break;
//            }
        }
    }

    private void updateCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
//        LatLng pos = new LatLng(currentLocationLat, currentLocationLng);
//        //Create MarkerOptions object
//        final MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(pos);
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.aircraft));
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (locationMarker != null) {
//                    locationMarker.remove();
//                }
//
//                if (checkGpsCoordination(currentLocationLat, currentLocationLng)) {
//                    locationMarker =  mMap.addMarker(markerOptions);
//                }
//            }
//        });
    }



    public static boolean checkGpsCoordination(double latitude, double longitude) {
        return (latitude > -90 && latitude < 90 && longitude > -180 && longitude < 180) && (latitude != 0f && longitude != 0f);
    }
    //Given the location, does a Google search for said location
    private void googleSearch(Location loc)
    {
        Uri mapIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + loc.getLat() + "," + loc.getLng() + "&query_place_id=" + loc.getPlaceID());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private Location getLocationFromList(String name)
    {
        for(Location l : locationList)
        {
            if(l.getName().equals(name)) return l;
        }
        return locationList.get(0);
    }

    //List of nightclubs in Richmond to use for app
    private void buildLocationList()
    {
        Location loc1 = new Location("Fallout", "Alternative night club, dress code enforced.", "117 N 18th St Richmond", "18");
        loc1.setLat(37.534087); loc1.setLng(-77.426085);
        loc1.setPlaceID("ChIJDaJUSR4RsYkROwtsj9LywTQ");
        locationList.add(loc1);
        Location loc2 = new Location("Plush RVA", "High-energy restaurant and lounge.", "1708 E Main St Richmond", "21");
        loc2.setLat(37.533445); loc2.setLng(-77.428019);
        loc2.setPlaceID("ChIJhcqh7R4RsYkRBOquJEU_CnQ");
        locationList.add(loc2);
        Location loc3 = new Location("Babe's of Carytown", "LGBTQ bar, daily lunch specials, live bands.", "3166 W Cary St Richmond", "21");
        loc3.setLat(37.553867); loc3.setLng(-77.482364);
        loc3.setPlaceID("ChIJd0rzBPATsYkRcDhnvaJ56oQ");
        locationList.add(loc3);
        Location loc4 = new Location("The Top", "Restaurant and lounge, live music.", "10 Walnut Alley Richmond", "21");
        loc4.setLat(37.533575); loc4.setLng(-77.427425);
        loc4.setPlaceID("ChIJe5Kx9h4RsYkRUE-laagF99o");
        locationList.add(loc4);
        Location loc5 = new Location("ODC After Hours", "After-hours night club.", "7 E Broad St Richmond", "21");
        loc5.setLat(37.545422); loc5.setLng(-77.441877);
        loc5.setPlaceID("ChIJSXHQXTkRsYkR0v95lIUyjr8");
        locationList.add(loc5);
        Location loc6 = new Location("Godfrey's", "Lively 18+ dance club.", "308 E Grace St Richmond", "18");
        loc6.setLat(37.543011); loc6.setLng(-77.439578);
        loc6.setPlaceID("ChIJ1bLdbzwRsYkRBhCsIzvq_MI");
        locationList.add(loc6);
        Location loc7 = new Location("Strange Matter", "Restaurant with retro arcade and live music.", "929 W Grace St Richmond", "21");
        loc7.setLat(37.550655); loc7.setLng(-77.452791);
        loc7.setPlaceID("ChIJW52WqkgRsYkRqebG6d587Pk");
        locationList.add(loc7);
        Location loc8 = new Location("Trifecta Richmond", "Night club featuring weekly guests.", "112 N 5th St Richmond", "21");
        loc8.setLat(37.541458); loc8.setLng(-77.438990);
        loc8.setPlaceID("ChIJAfrA_JsRsYkRClViZCnHC6k");
        locationList.add(loc8);
        Location loc9 = new Location("Image Restaurant and Lounge", "Upscale restaurant with nightlife.", "1713 E Main St Richmond", "21");
        loc9.setLat(37.533054); loc9.setLng(-77.428304);
        loc9.setPlaceID("ChIJtZyalh4RsYkReRAs9qo2rNU");
        locationList.add(loc9);
        Location loc10 = new Location("Kabana Rooftop", "Rooftop bar and lounge.", "700 E Main St Richmond", "21");
        loc10.setLat(37.539141); loc10.setLng(-77.437632);
        loc10.setPlaceID("ChIJ6WhsbD0RsYkRkz4ZKQDfKhY");
        locationList.add(loc10);
        Location loc11 = new Location("The Canal Club", "Live performance venue in an old, brick warehouse.", "1545 E Cary St Richmond", "All ages");
        loc11.setLat(37.532563); loc11.setLng(-77.429644);
        loc11.setPlaceID("ChIJWW3Hrh4RsYkRdbsAxe-G9Pw");
        locationList.add(loc11);
        Location loc12 = new Location("Cha Cha's", "Southwestern restaurant and bar known for its late-night scene.", "1419 E Cary St Richmond", "21");
        loc12.setLat(37.533915); loc12.setLng(-77.431817);
        loc12.setPlaceID("ChIJT5rAFRkRsYkRuTXruh3WrkY");
        locationList.add(loc12);
    }
public ArrayList<Location> getLocationList(){
        return this.locationList;
}
    //Updates parameters if user choses a different location
    @Override
    public void onLocationChanged(android.location.Location location)
    {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);
        locationManager.removeUpdates(this);
    }

    public void getLocation()
    {
        try
        {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
    }

    public GoogleMap getMap() {
        return mMap;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }
}