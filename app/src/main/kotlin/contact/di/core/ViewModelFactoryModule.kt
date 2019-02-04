package contact.di.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import contact.activity.container.ContainerActivityVM
import contact.activity.start.StartActivityVM
import contact.architecture.ViewModelFactory
import contact.architecture.ViewModelKey
import contact.fragment.contacts.ContactsVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ContactsVM::class)
    internal abstract fun contactsVM(VM: ContactsVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContainerActivityVM::class)
    internal abstract fun movieActivityVM(VM: ContainerActivityVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartActivityVM::class)
    internal abstract fun startActivityVM(VM: StartActivityVM): ViewModel
}
