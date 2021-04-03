package com.udacity.capstone.formula1.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.udacity.capstone.formula1.R
import com.udacity.capstone.formula1.database.F1Database
import com.udacity.capstone.formula1.dto.FavoriteLocation
import com.udacity.capstone.formula1.databinding.FragmentGrandPrixBinding
import com.udacity.capstone.formula1.dependencyinjection.Injection
import java.util.*

class GrandPrixFragment : Fragment(), OnMapReadyCallback {

    private val TAG = GrandPrixFragment::class.java.simpleName

    private val REQUEST_LOCATION_PERMISSION = 1

    private lateinit var grandPrixViewModel: GrandPrixViewModel
    private lateinit var binding: FragmentGrandPrixBinding
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGrandPrixBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val di = Injection()
        F1Database.getInstance(application)
        val viewModelFactory = GrandPrixViewModelFactory(di.repository, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(GrandPrixViewModel::class.java)
        grandPrixViewModel = viewModel

        val mapFragment = childFragmentManager.findFragmentById(R.id.googleMapSupport) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.saveButton.setOnClickListener {
            onMapLocationSelected()
        }

        grandPrixViewModel.favoriteLocations.observe(viewLifecycleOwner, androidx.lifecycle.Observer { it ->
            it.forEach {
                val snippet = String.format(
                    Locale.getDefault(),
                    "Lat: %1$.5f, Long: %2$.5f",
                    it.latitude,
                    it.longitude
                )
                val latlng = LatLng(it.latitude, it.longitude)
                map.addMarker(
                    MarkerOptions()
                        .position(latlng)
                        .title(getString(R.string.dropped_pin))
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                )
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.map_options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.normal_map -> {
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.hybrid_map -> {
            map.mapType = GoogleMap.MAP_TYPE_HYBRID
            true
        }
        R.id.satellite_map -> {
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        R.id.terrain_map -> {
            map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.size > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        //These coordinates represent the default location, which is the Portuguese GP location
        // Autódromo Internacional do Algarve
        val latitude = 37.23156372134667
        val longitude = -8.628295415534419
        val zoomLevel = 15f

        val portimao = LatLng(latitude, longitude)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(portimao, zoomLevel))
        map.addMarker(MarkerOptions().position(portimao))

        setMapStyle(map)
        setMapLongClick(map)
    }

    private fun onMapLocationSelected() {
        Toast.makeText(this.requireContext(), "Your locations are now saved! You can see them again next time you open the map.", Toast.LENGTH_LONG)

        this.findNavController().navigate(GrandPrixFragmentDirections.actionGrandPrixFragmentToMainFragment())
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            // A Snippet is Additional text that's displayed below the title.
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(getString(R.string.dropped_pin))
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))

            )
            val location = FavoriteLocation(0, latLng.latitude, latLng.longitude)
            grandPrixViewModel.saveNewFavoriteLocation(location)

            binding.saveButton.isEnabled = true
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.setMyLocationEnabled(true)
        }
        else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun setMapStyle(map: GoogleMap) {
        try {
            // Customize the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireActivity(),
                    R.raw.map_style
                )
            )

            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }
    }

    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION) === PackageManager.PERMISSION_GRANTED
    }
}