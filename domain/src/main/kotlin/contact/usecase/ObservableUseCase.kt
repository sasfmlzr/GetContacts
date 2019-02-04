package contact.usecase

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class ObservableUseCase<in Params, Result> {

    @Inject
    lateinit var threadExecutor: ThreadExecutor
    @Inject
    lateinit var postExecutionThread: PostExecutionThread

    open fun get(params: Params): Observable<Result> =
            buildUseCaseObservable(params)
                    .compose(applySchedulers)

    abstract fun buildUseCaseObservable(params: Params): Observable<Result>

    private val applySchedulers = ObservableTransformer<Result, Result> {
        it.subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecutionThread.getScheduler())
    }

}
