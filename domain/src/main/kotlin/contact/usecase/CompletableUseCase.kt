package contact.usecase

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class CompletableUseCase<in Params> {

    abstract fun buildUseCaseObservable(params: Params): Completable

}
