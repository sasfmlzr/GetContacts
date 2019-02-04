package contact.usecase

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class CompletableUseCase<in Params> {

    @Inject
    lateinit var threadExecutor: ThreadExecutor
    @Inject
    lateinit var postExecutionThread: PostExecutionThread

    open fun get(params: Params): Completable =
            buildUseCaseObservable(params)
                    .compose(applySchedulers)

    abstract fun buildUseCaseObservable(params: Params): Completable

    private val applySchedulers = CompletableTransformer {
        it.subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
    }

}
