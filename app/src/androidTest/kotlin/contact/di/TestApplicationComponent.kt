package contact.di

import contact.di.core.ApplicationComponent
import contact.di.core.ApplicationModule
import contact.di.mock.MockDataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    MockDataModule::class
])
interface TestApplicationComponent : ApplicationComponent {
    fun uiTestComponent(): UiTestComponent
}
