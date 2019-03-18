package contact.di.core

import contact.di.mock.MockDataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    MockDataModule::class
])
interface MockApplicationComponent : ApplicationComponent
