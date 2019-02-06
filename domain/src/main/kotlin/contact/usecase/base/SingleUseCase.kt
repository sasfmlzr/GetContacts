package contact.usecase.base

import io.reactivex.Single

abstract class SingleUseCase<in Params, Result> {

    abstract fun buildUseCaseObservable(params: Params): Single<Result>

}
