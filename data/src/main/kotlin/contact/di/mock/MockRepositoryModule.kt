package contact.di.mock

import contact.repository.ContactRepository
import contact.repository.MockContactRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class MockRepositoryModule {

    @Binds
    @Singleton
    internal abstract fun contactRepository(contactRepository: MockContactRepository): ContactRepository

}
