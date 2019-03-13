package contact.di

import contact.utils.LocationUtils
import contact.utils.LocalLocationUtils
import dagger.Binds
import dagger.Module

@Module
internal abstract class UtilsModule {

    @Binds
    internal abstract fun locationUtils(localLocationUtils: LocalLocationUtils): LocationUtils

}
