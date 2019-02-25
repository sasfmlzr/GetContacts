package contact.fragment.location

import android.content.Context
import android.location.Location
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
import contact.architecture.base.ui.Ui
import contact.architecture.logging.Logger
import contact.pipe.contactsowner.ObserveContactsOwnerEventModel
import contact.pipe.contactsowner.RequestObserveContactsOwnerEvent
import javax.inject.Inject

class LocationUi @Inject constructor(
        private val context: Context,
        private val logger: Logger
) : Ui<LocationModel>(), GoogleMapCallback {

    private lateinit var map: SupportMapFragment

    override fun getMap(supportMapFragment: SupportMapFragment) {
        map = supportMapFragment
    }

    private fun something(location: Location) {
        map.getMapAsync { googleMap ->
            val place = LatLng(location.latitude,
                    location.longitude)
            googleMap.addMarker(MarkerOptions().position(place).title("I Here"))
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(place))
        }
    }

    @BindView(R.id.contacts)
    lateinit var contactView: TextView

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        eventSource.onNext(RequestObserveContactsOwnerEvent())
    }

    private val onMapCallback = OnMapReadyCallback { googleMap ->
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun render(model: LocationModel) {
        when (model.eventModel) {
            is ObserveContactsOwnerEventModel -> {
                // configureAdapter(model.eventModel.contacts!!)
            }
        }
    }

    @OnClick(R.id.button)
    fun pushButton() {
    }


}
