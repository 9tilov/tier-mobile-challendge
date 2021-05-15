package com.d9tilov.android.tiertestapp.map.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.d9tilov.android.tiertestapp.R
import com.d9tilov.android.tiertestapp.base.ui.MapNavigator
import com.d9tilov.android.tiertestapp.extensions.bitmapDescriptorFromVector
import com.d9tilov.android.tiertestapp.settings.ui.SettingsFragment
import com.d9tilov.android.tiertestapp.vehicle.ui.VehicleViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    MapNavigator {

    private lateinit var map: GoogleMap
    private val viewModel by viewModels<VehicleViewModel>()
    private var isZoomSetUp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.navigator = this
        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMarkerClickListener(this)
        viewModel.vehicles().observe(this, { result ->
            result.data?.let {
                if (it.isEmpty()) {
                    return@observe
                }
                map.clear()
                val latLngBuilder = LatLngBounds.Builder()
                for (item in it) {
                    val latlng = LatLng(item.latitude, item.longitude)
                    val markerOptions = MarkerOptions().position(latlng)
                    when (item.model.name) {
                        "AA" -> markerOptions.icon(bitmapDescriptorFromVector(R.drawable.ic_baseline_directions_car_24))
                        "AB" -> markerOptions.icon(bitmapDescriptorFromVector(R.drawable.ic_baseline_electric_bike_24))
                        "UNKNOWN" -> markerOptions.icon(bitmapDescriptorFromVector(R.drawable.ic_baseline_directions_bike_24))
                    }
                    val marker: Marker? = map.addMarker(markerOptions)
                    marker?.run { tag = item.id }
                    latLngBuilder.include(latlng)
                }
                if (!isZoomSetUp) {
                    val size = resources.displayMetrics.widthPixels
                    val latLngBounds = latLngBuilder.build()
                    val track = CameraUpdateFactory.newLatLngBounds(latLngBounds, size, size, 25)
                    map.animateCamera(track)
                    isZoomSetUp = true
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                viewModel.goToSettings()
            }
        }
        return true
    }

    override fun openSettings() {
        supportFragmentManager.let {
            SettingsFragment.newInstance().apply {
                show(it, tag)
            }
        }
    }

    override fun openBookMenu(vehicleId: Long) {
        supportFragmentManager.let {
            BookVehicleFragment.newInstance(vehicleId).apply {
                show(it, tag)
            }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        viewModel.openBookMenu(marker.tag as Long)
        return true
    }
}