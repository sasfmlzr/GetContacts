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
import contact.pipe.location.*
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import javax.inject.Inject

class LocationUi @Inject constructor() : Ui<LocationModel>(), GoogleMapCallback {

    private lateinit var map: SupportMapFragment

    override fun getMap(supportMapFragment: SupportMapFragment) {
        map = supportMapFragment
    }

    private var nameOwner = ""
    private lateinit var filterButton: View
    private lateinit var minDate: LocalDateTime
    private lateinit var maxDate: LocalDateTime
    private val dateTimeNow = LocalDateTime.now()

    override fun bindViews(view: View): Unbinder = ButterKnife.bind(this, view)

    override fun onCreate() {
        super.onCreate()
        configureToolbar()
    }

    override fun render(model: LocationModel) {
        when (model.eventModel) {
            is BundleModel -> {
                nameOwner = model.eventModel.nameOwner

                eventSource.onNext(RequestLocationsByIdAndDateEvent(
                        nameOwner,
                        dateTimeNow.minusMonths(1).toLocalDate(),
                        dateTimeNow.toLocalDate()))
            }
            is LocationEventModel -> {
                eventSource.onNext(RequestMinMaxDateEvent(model.eventModel.locations))
                configureLocations(model.eventModel.locations)
            }
            is MinMaxDateEventModel -> {
                minDate = model.eventModel.minDate
                maxDate = model.eventModel.maxDate
            }
            is ToolbarEventModel -> actionBar?.title = model.eventModel.title
        }
    }

    private fun configureToolbar() {
        actionBar?.title = ""
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
                    minDate,
                    maxDate,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        newDate = LocalDate(year, monthOfYear + 1, dayOfMonth)
                        eventSource.onNext(RequestLocationsByIdAndDateEvent(
                                nameOwner,
                                oldDate!!,
                                newDate)
                        )
                    }
            )
        }

        extensions?.showDatePickerDialog(
                "From",
                dateTimeNow!!,
                minDate,
                maxDate,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    oldDate = LocalDate(year, monthOfYear + 1, dayOfMonth)
                    newDatePickerDialog.run { }
                }
        )
    }

    private fun configureLocations(locations: List<GetLocation>) {
        map.getMapAsync { googleMap ->
            googleMap.clear()
            val polyLines = PolylineOptions()

            locations.forEach {
                val place = LatLng(it.latitude, it.longitude)
                polyLines.add(place)
                googleMap.addMarker(MarkerOptions().position(place)
                        .title(it.date.toString("dd.MM.yyyy HH:mm:ss")))
            }

            val lastPlace = LatLng(locations.last().latitude, locations.last().longitude)

            googleMap.addPolyline(polyLines)
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(lastPlace))
        }
    }
}
