package contact.di.core

import contact.BuildConfig

object Injector {

    lateinit var component: ApplicationComponent

    fun prepare(application: MainApplication) {
        if (!::component.isInitialized) {
            component = when(BuildConfig.MOCK_ARCHITECTURE) {
                true -> DaggerMockApplicationComponent.builder()
                        .applicationModule(ApplicationModule(application))
                        .build()
                false -> DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(application))
                        .build()
            }
        }
    }

    fun applicationComponent() = component
    fun navigationComponent() = component.navigationComponent()
    fun viewComponent() = component.viewComponent()
}
