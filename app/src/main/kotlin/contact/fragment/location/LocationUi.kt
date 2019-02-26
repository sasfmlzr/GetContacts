package contact.fragment.location

import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import contact.R
import contact.api.location.GetLocation
import contact.architecture.GoogleMapCallback
import contact.architecture.base.ui.Ui
import contact.pipe.location.LocationEventModel
import contact.pipe.location.RequestLocationsByIdEvent
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import javax.inject.Inject

class LocationUi @Inject constructor() : Ui<LocationModel>(), GoogleMapCallback {

    private lateinit var map: SupportMapFragment

    override fun getMap(supportMapFragment: SupportMapFragment) {
        map = supportMapFragment
    }

    private lateinit var wtf: View

    private var minDate: LocalDateTime? = null
    private var maxDate: LocalDateTime? = null

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        eventSource.onNext(RequestLocationsByIdEvent("alexey"))

        actionBar?.title = "Last month"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar?.apply {
            post {
                inflateMenu(R.menu.location_menu)
                wtf = findViewById(R.id.menu_filter)
            }

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_filter -> showFilter().let { true }
                    else -> false
                }
            }
        }
    }

    private fun showFilter() {
        val localDateTime = LocalDate.now()
        val datePickerDialog = SpinnerDatePickerDialogBuilder()
                .context(wtf.context)
                .callback { view, year, monthOfYear, dayOfMonth ->
                    {

                    }
                }
                .defaultDate(localDateTime.year,
                        localDateTime.monthOfYear - 1,
                        localDateTime.dayOfMonth)
                .minDate(minDate!!.year,
                        minDate!!.monthOfYear - 1,
                        minDate!!.dayOfMonth)
                .maxDate(maxDate!!.year,
                        maxDate!!.monthOfYear - 1,
                        maxDate!!.dayOfMonth)
                .build()
                .show()
    }

    override fun render(model: LocationModel) {
        when (model.eventModel) {
            is LocationEventModel -> {
                loadMinMaxDate(model.eventModel.locations)
                configureLocations(model.eventModel.locations)
            }
        }
    }

    fun loadMinMaxDate(locations: List<GetLocation>) {
        locations.forEach {
            if (minDate == null) {
                minDate = it.date
            }
            if (it.date < minDate) {
                minDate = it.date
            }

            if (maxDate == null) {
                maxDate = it.date
            }
            if (it.date > maxDate) {
                maxDate = it.date
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
}
