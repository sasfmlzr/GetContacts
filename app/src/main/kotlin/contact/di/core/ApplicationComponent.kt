package contact.di.core

import contacts.di.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class, DataModule::class
])
interface ApplicationComponent {
    fun viewComponent(): FragmentComponent
    fun navigationComponent(): ActivityComponent
    fun inject(mainApplication: MainApplication)
}
