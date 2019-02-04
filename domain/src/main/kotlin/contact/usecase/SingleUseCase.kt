package contact.usecase

import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class SingleUseCase<in Params, Result> {

    @Inject
    lateinit var threadExecutor: ThreadExecutor
    @Inject
    lateinit var postExecutionThread: PostExecutionThread

    open fun get(params: Params): Single<Result> =
            buildUseCaseObservable(params)
                    .compose(applySchedulers)

    abstract fun buildUseCaseObservable(params: Params): Single<Result>

    private val applySchedulers = SingleTransformer<Result, Result> {
        it.subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecutionThread.getScheduler())
    }

}
