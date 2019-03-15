package contact.di.core

import contact.BuildConfig

object Injector {

    private lateinit var component: ApplicationComponent

    fun prepare(application: MainApplication) {
        component = when(BuildConfig.MOCK_ARCHITECTURE) {
            true -> DaggerMockApplicationComponent.builder()
                    .applicationModule(ApplicationModule(application))
                    .build()
            false -> DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(application))
                    .build()
        }
    }

    fun applicationComponent() = component
    fun navigationComponent() = component.navigationComponent()
    fun viewComponent() = component.viewComponent()
}
