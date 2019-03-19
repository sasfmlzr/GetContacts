package contact.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import contact.R
import contact.activity.container.ContainerActivity
import contact.di.DaggerTestApplicationComponent
import contact.di.core.ApplicationModule
import contact.di.core.Injector
import contact.di.core.MainApplication
import contact.usecase.feature.InitUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class ContainerActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(ContainerActivity::class.java,
            false,
            false)

    @Inject
    lateinit var initUseCase: InitUseCase

    @Before
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<MainApplication>()

        val testAppComponent = DaggerTestApplicationComponent.builder()
                .applicationModule(ApplicationModule(app))
                .build()
        Injector.component = testAppComponent
        testAppComponent.uiTestComponent().inject(this)
    }

    @Test
    fun exp() {
        initUseCase.buildUseCaseObservable(Unit)
                .subscribe()
        activityTestRule.launchActivity(null)
        //   //do something

        activityTestRule.finishActivity()
    }

    private fun nav() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply { putString("idNameOwner", "test") }
                findNavController(R.id.navHostFragment)
                        .navigate(R.id.navLocationFragment, bundle)
            }
        }
    }
}
