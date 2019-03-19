package contact.test

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import contact.R
import contact.activity.start.StartActivity
import contact.di.DaggerTestApplicationComponent
import contact.di.core.ApplicationModule
import contact.di.core.Injector
import contact.di.core.MainApplication
import contact.fragment.contactsowner.list.ContactsOwnerListAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StartActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(StartActivity::class.java,
            false,
            false)

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
    fun verify_Start_Activity() {
        activityTestRule.launchActivity(null)
        prepareData()

        activityTestRule.finishActivity()
    }

    @Test
    fun verify_Container_Activity() {
        activityTestRule.launchActivity(null)
        prepareData()

        onView(withId(R.id.recycler_view_owner_contacts))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition
                        <ContactsOwnerListAdapter.ParentViewHolder>(0, click()))

        activityTestRule.finishActivity()
    }

    private fun prepareData() {
        onView(withId(R.id.firstInitText)).check(matches(withText("Initialization")))

        onView(withId(R.id.buttonRetry))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        Thread.sleep(2000)
        onView(withId(R.id.recycler_view_owner_contacts))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}
