package contact.di.core

import contact.di.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DataModule::class
])
interface MockApplicationComponent : ApplicationComponent