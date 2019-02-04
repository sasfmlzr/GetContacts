package contact.usecase

interface ExecutorResult {
    fun onSuccess()
    fun onFailure(t: Throwable)
}
