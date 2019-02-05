package contact.usecase

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class ObservableUseCase<in Params, Result> {

    abstract fun buildUseCaseObservable(params: Params): Observable<Result>

}
