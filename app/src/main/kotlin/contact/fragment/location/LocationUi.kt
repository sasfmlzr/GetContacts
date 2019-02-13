package contact.fragment.location

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import contact.R
import contact.architecture.GoogleMapCallback
import contact.architecture.PermissionsCallback
import contact.architecture.base.ui.Ui
import contact.architecture.logging.Logger
import contact.pipe.contacts.ContactsInitEventModel
import contact.pipe.contacts.ContactsPushEventModel
import contact.pipe.contacts.ObserveContactsEventModel
import javax.inject.Inject

class LocationUi @Inject constructor(
        private val context: Context,
        private val logger: Logger
) : Ui<LocationModel>(), PermissionsCallback, GoogleMapCallback {

    private lateinit var map: SupportMapFragment

    override fun getMap(supportMapFragment: SupportMapFragment) {
        map = supportMapFragment
    }

    override fun onPermissionsGranted(permissions: List<String>) {
        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                // Called when a new location is found by the network location provider.
                //   makeUseOfNewLocation(location)
                print("asdasd")
                map.getMapAsync { googleMap ->
                    val place = LatLng(location.latitude,
                            location.longitude)
                    googleMap.addMarker(MarkerOptions().position(place).title("I Here"))
                    googleMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(place))
                }
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            }

            override fun onProviderEnabled(provider: String) {
            }

            override fun onProviderDisabled(provider: String) {
            }
        }
        val locationService = LocationService.getLocationManager(context, locationListener)
    }

    @BindView(R.id.contacts)
    lateinit var contactView: TextView

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        //  eventSource.onNext(ContactsInitEvent()
        map.getMapAsync(onMapCallback)
    }

    private val onMapCallback = OnMapReadyCallback { googleMap ->
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun render(model: LocationModel) {
        when (model.eventModel) {
            is ContactsInitEventModel -> contactView.text = model.eventModel.contactsResult
            is ContactsPushEventModel -> contactView.text = model.eventModel.contactsPushResult
            is ObserveContactsEventModel -> contactView.text = model.eventModel.toString()
        }
        logger.d("ContactsUi", "RENDER EXECUTED")
    }

    @OnClick(R.id.button)
    fun pushNutton() {
        //   eventSource.onNext(RequestObserveContactsEvent())
        //    eventSource.onNext(ContactsPushEvent())

        val permissions = listOf(
                Manifest.permission.ACCESS_FINE_LOCATION
        )
        runtimePermissions.request(permissions)

    }


}
