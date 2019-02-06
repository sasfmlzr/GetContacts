package contacts.di

import contact.storage.ContactStorage
import contacts.storage.LocalContactStorage
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class StorageModule {

    @Binds
    @Singleton
    internal abstract fun contactStorage(contactStorage: LocalContactStorage): ContactStorage
}