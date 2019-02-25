package contact.fragment.location

import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import contact.R
import contact.api.location.GetLocation
import contact.architecture.GoogleMapCallback
import contact.architecture.base.ui.Ui
import contact.pipe.location.LocationEventModel
import contact.pipe.location.RequestLocationsByIdEvent
import javax.inject.Inject

class LocationUi @Inject constructor() : Ui<LocationModel>(), GoogleMapCallback {

    private lateinit var map: SupportMapFragment

    override fun getMap(supportMapFragment: SupportMapFragment) {
        map = supportMapFragment
    }

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        eventSource.onNext(RequestLocationsByIdEvent("alexey"))
    }

    override fun render(model: LocationModel) {
        when (model.eventModel) {
            is LocationEventModel -> {
                configureLocations(model.eventModel.locations)
            }
        }
    }

    fun configureLocations(locations: List<GetLocation>) {
        map.getMapAsync { googleMap ->

            val polyLines = PolylineOptions()

            locations.forEach {
                val place = LatLng(it.latitude, it.longitude)
                polyLines.add(place)
                googleMap.addMarker(MarkerOptions().position(place).title(it.date.toString()))

            }

            val lastPlace = LatLng(locations.last().latitude, locations.last().longitude)

            googleMap.addPolyline(polyLines)
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(lastPlace))
        }
    }

    @OnClick(R.id.button)
    fun pushButton() {
    }
}
