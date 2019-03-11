package contact.architecture

import contact.architecture.base.ui.BaseModel
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

abstract class Presenter<S : ViewState> : ObservableTransformer<S, BaseModel> {

    protected abstract fun map(state: S): BaseModel

    override fun apply(upstream: Observable<S>): ObservableSource<BaseModel> =
            upstream.map(::map)

}
