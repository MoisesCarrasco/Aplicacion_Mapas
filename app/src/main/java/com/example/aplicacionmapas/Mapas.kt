package com.example.aplicacionmapas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionmapas.databinding.ActivityMapsBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class Mapas : AppCompatActivity(), OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Mostrar los controles de zoom
        mMap.uiSettings.isZoomControlsEnabled = true

        // Establecer la posición y el título del marcador
        val Itvo = LatLng(17.02074882219381, -96.763972513466)
        mMap.addMarker(
            MarkerOptions()
                .position(Itvo)
                .title("ITVO")
                .snippet("Universidad Publica")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )

        // Establecer el tipo de mapa en híbrido
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        // Mover la cámara a la posición del marcador
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Itvo))

        // Establecer el nivel de zoom de la cámara
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15f))

        // Habilitar el tráfico en el mapa
        mMap.isTrafficEnabled = true

        // Establecer el listener para el evento de clic en la ventana de información del marcador
        mMap.setOnInfoWindowClickListener(this)

        // Establecer el adaptador para personalizar la ventana de información del marcador
        mMap.setInfoWindowAdapter(this)
    }

    override fun onInfoWindowClick(marker: Marker) {
        // Manejar el evento de clic en la ventana de información del marcador
        val message = "Tecnm Valle de Oaxaca ${marker.title}"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun getInfoWindow(marker: Marker): View? {
        // layout personalizado para la ventana de información
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.info_window_layout, null)

        // Obtener las referencias a los TextViews
        val titleView = view.findViewById<TextView>(R.id.title)
        val snippetView = view.findViewById<TextView>(R.id.snippet)

// Establecer el contenido de los TextViews con la información
        titleView.text = marker.title
        snippetView.text = marker.snippet

        return view
    }

    override fun getInfoContents(marker: Marker): View? {
        return null
    }
}
