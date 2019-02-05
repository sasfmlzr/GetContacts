package contact.architecture.base.ui

import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import butterknife.Unbinder
import contact.architecture.EventSource
import contact.architecture.Extensions

abstract class Ui<in UM : UiModel> {

    protected lateinit var eventSource: EventSource
    protected var extensions: Extensions? = null
    protected var actionBar: ActionBar? = null
    protected var toolbar: Toolbar? = null

    private lateinit var unbinder: Unbinder

    fun init(toolkit: UiToolkit) {
        eventSource = toolkit.eventSource
        extensions = toolkit.extensions
        actionBar = toolkit.actionBar
        toolbar = toolkit.toolbar
        unbinder = bindViews(toolkit.view)
        onCreate()
    }

    abstract fun bindViews(view: View): Unbinder

    @CallSuper
    open fun onCreate() {
    }

    @CallSuper
    open fun onDestroy() {
        unbinder.unbind()
    }

    abstract fun render(model: UM)

}
