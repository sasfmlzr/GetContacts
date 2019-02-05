package contact.architecture.base.ui

import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import contact.architecture.EventSource
import contact.architecture.Extensions

data class UiToolkit(
        val view: View,
        val actionBar: ActionBar?,
        val toolbar: Toolbar?,
        val eventSource: EventSource,
        val extensions: Extensions
)
