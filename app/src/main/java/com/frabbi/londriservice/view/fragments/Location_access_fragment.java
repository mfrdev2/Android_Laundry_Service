package com.frabbi.londriservice.view.fragments;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frabbi.londriservice.R;
import com.frabbi.londriservice.databinding.FragmentLocationAccessFragmentBinding;
import com.frabbi.londriservice.model.AddressModel;
import com.frabbi.londriservice.model.OrderGettingModel;
import com.frabbi.londriservice.roomdb.entities.OrderList;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class Location_access_fragment extends Fragment {
    private FragmentLocationAccessFragmentBinding fBinding;
    private SupportMapFragment smf;
    private FusedLocationProviderClient client;
    private List<OrderGettingModel> orderConfirmList;
    private OrderList orderObject;


    public Location_access_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fBinding = FragmentLocationAccessFragmentBinding.inflate(inflater, container, false);
        smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_mapId);
        return fBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        locationTask();
        assert getArguments() != null;
        orderConfirmList = (List<OrderGettingModel>) getArguments().getSerializable("orderConfirm");
        orderObject = (OrderList) getArguments().getSerializable("orderObject");

        fBinding.addButtonId.setOnClickListener(v -> {
            String mapTextField = fBinding.mapLocation.getText().toString();
            String homeTextField = fBinding.homeAddressId.getText().toString();
            String numberTextField = fBinding.phoneNoId.getText().toString();
            if (isValidInput(mapTextField, homeTextField, numberTextField)) {
                AddressModel data = new AddressModel(
                        mapTextField,
                        homeTextField,
                        numberTextField
                );

                Bundle bundle = new Bundle();
                bundle.putSerializable("orderObject", (Serializable) orderObject);
                bundle.putSerializable("data", (Serializable) data);
                bundle.putSerializable("orderConfirm", (Serializable) orderConfirmList);
                Navigation.findNavController(view).navigate(R.id.action_location_access_fragment_to_checkoutFragment, bundle);
            } else {
                Toast.makeText(requireContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private Boolean isValidInput(String mapTextField, String homeTextField, String numberTextField) {

        if (!mapTextField.isEmpty() && !homeTextField.isEmpty() && !numberTextField.isEmpty()) {
            return true;
        }

        return false;
    }

    private void locationTask() {
        client = LocationServices.getFusedLocationProviderClient(requireContext());
        Dexter.withContext(requireContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getMyLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        /* Task<Location> task = */
        Task<Location> lastLocation = client.getLastLocation();
        lastLocation.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                smf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        if (location != null) {
                            LatLng latLng = new LatLng(location.getLatitude()
                                    , location.getLongitude());
                            Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                            StringBuilder homeAddress;
                            try {
                                homeAddress = new StringBuilder();
                                Address address;
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                for (int index = 0; index < addresses.size(); ++index) {
                                    address = addresses.get(index);
                                homeAddress.append("City: " + address.getLocality() + "\n");  //Dhaka
                                 /*      homeAddress.append("Sub-Admin Ares: " + address.getSubAdminArea() + "\n"); //Dhaka District
                                    homeAddress.append("Admin Area: " + address.getAdminArea() + "\n");      //Dhaka Division
                                    homeAddress.append("Country: " + address.getCountryName() + "\n");       //Bangladesh
                                    homeAddress.append("Country Code: " + address.getCountryCode() + "\n");  //BD*/
                                    homeAddress.append("address "+ address.getAddressLine(0)+"\n");
                                    homeAddress.append("Latitude: " + address.getLatitude() + "\n");
                                    homeAddress.append("Longitude: " + address.getLongitude() + "\n\n");



                                    Log.i("TAG", "onMapReady: " + address);
                                }
                                fBinding.mapLocation.setText(homeAddress.toString());
                            } catch (Exception e) {

                            }





                            MarkerOptions markerOptions =
                                    new MarkerOptions().position(latLng)
                                            .title("You are in...");

                            googleMap.addMarker(markerOptions);
                            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            googleMap.setMyLocationEnabled(true);
                            googleMap.setMinZoomPreference(12.0f); //6.0f
                            googleMap.setMaxZoomPreference(18.0f); //14.0f
                        }


                    }
                });
            }
        });
    }
}