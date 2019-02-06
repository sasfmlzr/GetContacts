package contact.architecture

class LoadingStartedEventModel : EventModel()

class LoadingEndedEventModel : EventModel()

data class ErrorEventModel(val error: Throwable) : EventModel()

class HideErrorEventModel : EventModel()
