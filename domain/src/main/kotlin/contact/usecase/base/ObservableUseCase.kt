package contact.usecase.base

import io.reactivex.Observable

abstract class ObservableUseCase<in Params, Result> {

    abstract fun buildUseCaseObservable(params: Params): Observable<Result>

}
