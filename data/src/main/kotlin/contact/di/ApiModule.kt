package contact.di

import contact.api.ContactsApi
import contact.api.getOwnerContactConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
internal class ApiModule {
    companion object {
        private const val CONTACT_URL = "192.168.1.112"
        private const val port = 8000
    }

    @Provides
    @Singleton
    fun getContactsRetrofitService(): ContactsApi {
        val httpUrl = HttpUrl.Builder()
                .host(CONTACT_URL)
                .port(port)
                .scheme("http")
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(httpUrl)
                .addConverterFactory(getOwnerContactConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(ContactsApi::class.java)
    }
}
