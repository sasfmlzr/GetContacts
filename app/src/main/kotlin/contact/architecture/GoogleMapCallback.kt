package contact.architecture

import com.google.android.gms.maps.SupportMapFragment

interface GoogleMapCallback {
    fun getMap(supportMapFragment: SupportMapFragment)
}