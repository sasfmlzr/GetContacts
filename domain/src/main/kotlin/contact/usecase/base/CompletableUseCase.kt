package contact.usecase.base

import io.reactivex.Completable

abstract class CompletableUseCase<in Params> {

    abstract fun buildUseCaseObservable(params: Params): Completable

}
