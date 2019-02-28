package contacts.di

import dagger.Module

@Module(includes = [
    ApiModule::class,
    RepositoryModule::class,
    StorageModule::class,
    UtilsModule::class
])
class DataModule
