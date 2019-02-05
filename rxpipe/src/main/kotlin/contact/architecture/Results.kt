package contact.architecture

class LoadingStartedResult : Result()

class LoadingEndedResult : Result()

data class ErrorResult(val error: Throwable) : Result()

class HideErrorResult : Result()
