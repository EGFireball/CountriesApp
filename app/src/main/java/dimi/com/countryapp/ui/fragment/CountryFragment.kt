package dimi.com.countryapp.ui.fragment

import android.location.Geocoder
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dimi.com.countryapp.R
import dimi.com.countryapp.domain.Country
import kotlinx.android.synthetic.main.country_fragment.*

class CountryFragment : Fragment(), GoogleMap.OnMapLoadedCallback, OnMapReadyCallback {

    private var country: Country? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.country_fragment, container, false)

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(R.id.action_search)
        if (item != null)
            item.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        country = arguments?.let { CountryFragmentArgs.fromBundle(it).country }

        countryName.text = country?.name
        capital.text = getString(R.string.country_capital, country?.capital)
        countryInfo.text = getString(R.string.country_info, country?.name, country?.population, country?.area.toString())

        initMapView(savedInstanceState)
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        map.onCreate(savedInstanceState)
        map.onResume()

        try {
            MapsInitializer.initialize(activity?.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        map.getMapAsync(this)
    }

    override fun onMapReady(gmap: GoogleMap?) {
        val geocoder = Geocoder(activity)
        val addresses = geocoder.getFromLocationName("${country?.capital},${country?.name}", 1)
        val mapZoomPreference = 7f
        gmap?.setMinZoomPreference(mapZoomPreference)
        val capitalPosition = LatLng(addresses[0].latitude, addresses[0].longitude)
        gmap?.addMarker(MarkerOptions().position(capitalPosition).title("${country?.capital}"))
        gmap?.moveCamera(CameraUpdateFactory.newLatLng(capitalPosition))
    }

    override fun onMapLoaded() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}