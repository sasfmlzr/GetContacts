package contacts.di

import contact.utils.LocationUtils
import contacts.utils.LocalLocationUtils
import dagger.Binds
import dagger.Module

@Module
internal abstract class UtilsModule {

    @Binds
    internal abstract fun locationUlils(localLocationUtils: LocalLocationUtils): LocationUtils

}
