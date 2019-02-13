package contact.di.core

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import contact.architecture.logging.AndroidLogger
import contact.architecture.logging.Logger
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MainApplication) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application = application

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    internal fun logger(androidLogger: AndroidLogger): Logger = androidLogger
}
