package contact.architecture

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.tsongkha.spinnerdatepicker.DatePickerDialog
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import contact.R
import org.joda.time.LocalDateTime

class Extensions(
        private val activity: FragmentActivity
) {
    fun showMessage(text: String): Unit? =
            activity.findViewById<View>(android.R.id.content)
                    ?.let { Snackbar.make(it, text, Snackbar.LENGTH_LONG).show() }

    fun showDatePickerDialog(title: String,
                             dateTimeNow: LocalDateTime,
                             minDate: LocalDateTime,
                             maxDate: LocalDateTime,
                             callback: DatePickerDialog.OnDateSetListener) {
        val datePicker = SpinnerDatePickerDialogBuilder()
                .context(activity)
                .callback(callback)
                .spinnerTheme(R.style.CustomNumberPickerStyle)
                .dialogTheme(R.style.CustomDialogStyle)
                .defaultDate(dateTimeNow.year,
                        dateTimeNow.monthOfYear - 1,
                        dateTimeNow.dayOfMonth)
                .minDate(minDate.year,
                        minDate.monthOfYear - 1,
                        minDate.dayOfMonth)
                .maxDate(maxDate.year,
                        maxDate.monthOfYear - 1,
                        maxDate.dayOfMonth)
                .build()
        datePicker.setTitle(title)
        datePicker.show()
    }
}
