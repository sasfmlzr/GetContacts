package contact.fragment.location

import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.tsongkha.spinnerdatepicker.DatePickerDialog
import contact.R
import contact.api.location.GetLocation
import contact.architecture.GoogleMapCallback
import contact.architecture.base.ui.Ui
import contact.pipe.location.LocationEventModel
import contact.pipe.location.RequestLocationsByIdAndDateEvent
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import javax.inject.Inject

class LocationUi @Inject constructor() : Ui<LocationModel>(), GoogleMapCallback {

    private lateinit var map: SupportMapFragment

    override fun getMap(supportMapFragment: SupportMapFragment) {
        map = supportMapFragment
    }

    private lateinit var filterButton: View

    private var minDate: LocalDateTime? = null
    private var maxDate: LocalDateTime? = null
    private val dateTimeNow = LocalDateTime.now()

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        eventSource.onNext(RequestLocationsByIdAndDateEvent(
                "alexey",
                dateTimeNow.minusMonths(1).toLocalDate(),
                dateTimeNow.toLocalDate()))

        actionBar?.title = "Last month"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar?.apply {
            post {
                inflateMenu(R.menu.location_menu)
                filterButton = findViewById(R.id.menu_filter)
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
        var oldDate: LocalDate? = null
        var newDate: LocalDate

        val newDatePickerDialog: Unit by lazy {
            extensions!!.showDatePickerDialog(
                    "To",
                    dateTimeNow!!,
                    minDate!!,
                    maxDate!!,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        newDate = LocalDate(year, monthOfYear + 1, dayOfMonth)
                        eventSource.onNext(RequestLocationsByIdAndDateEvent(
                                "alexey",
                                oldDate!!,
                                newDate)
                        )
                    }
            )
        }

        extensions?.showDatePickerDialog(
                "From",
                dateTimeNow!!,
                minDate!!,
                maxDate!!,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    oldDate = LocalDate(year, monthOfYear + 1, dayOfMonth)
                    newDatePickerDialog.run { }
                }
        )
    }

    override fun render(model: LocationModel) {
        when (model.eventModel) {
            is LocationEventModel -> {
                loadMinMaxDate(model.eventModel.locations)
                configureLocations(model.eventModel.locations)
            }
        }
    }

    private fun loadMinMaxDate(locations: List<GetLocation>) {
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
            googleMap.clear()
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
