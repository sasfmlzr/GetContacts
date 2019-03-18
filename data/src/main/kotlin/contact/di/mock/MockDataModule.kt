package contact.di.mock

import contact.di.StorageModule
import contact.di.UtilsModule
import dagger.Module

@Module(includes = [
    MockRepositoryModule::class,
    StorageModule::class,
    UtilsModule::class
])
class MockDataModule
