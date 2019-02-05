package contact.usecase

import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class SingleUseCase<in Params, Result> {

    abstract fun buildUseCaseObservable(params: Params): Single<Result>

}
