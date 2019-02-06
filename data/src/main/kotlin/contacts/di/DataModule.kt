package contacts.di

import dagger.Module

@Module(includes = [
    (ApiModule::class),
    (RepositoryModule::class),
    (StorageModule::class)
])
class DataModule {
}