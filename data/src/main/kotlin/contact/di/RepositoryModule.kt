package contact.di

import contact.repository.ContactRepository
import contact.repository.NetworkContactRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun contactRepository(contactRepository: NetworkContactRepository): ContactRepository

}
