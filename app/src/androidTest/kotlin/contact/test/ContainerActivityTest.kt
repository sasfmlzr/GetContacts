package contact.test

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import contact.R
import contact.activity.container.ContainerActivity
import contact.di.DaggerTestApplicationComponent
import contact.di.core.ApplicationModule
import contact.di.core.Injector
import contact.di.core.MainApplication
import contact.fragment.contactsowner.list.ContactsOwnerListAdapter
import contact.usecase.feature.InitUseCase
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
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
    fun check_RecyclerView_Work() {
        initUseCase.buildUseCaseObservable(Unit)
                .subscribe()
        activityTestRule.launchActivity(null)

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_owner_contacts))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition
                        <ContactsOwnerListAdapter.ParentViewHolder>(0, ViewActions.click()))

        activityTestRule.finishActivity()
    }

    @Test
    fun check_Navigation_Work() {
        initUseCase.buildUseCaseObservable(Unit)
                .subscribe()
        activityTestRule.launchActivity(null)

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_owner_contacts))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition
                        <ContactsOwnerListAdapter.ParentViewHolder>(0, ViewActions.click()))

        navigateToNavigationFragment()

        val actionMenuItemView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.menu_filter),
                        ViewMatchers.withContentDescription("Filter"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(R.id.toolbar),
                                        2),
                                0),
                        ViewMatchers.isDisplayed()))
        actionMenuItemView.perform(ViewActions.click())

        val appCompatButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(android.R.id.button1),
                        ViewMatchers.withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(R.id.buttonPanel),
                                        0),
                                3)))
        appCompatButton.perform(ViewActions.scrollTo(), ViewActions.click())

        val appCompatButton2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(android.R.id.button1),
                        ViewMatchers.withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(R.id.buttonPanel),
                                        0),
                                3)))
        appCompatButton2.perform(ViewActions.scrollTo(), ViewActions.click())

        activityTestRule.finishActivity()
    }

    private fun navigateToNavigationFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply { putString("idNameOwner", "test") }
                findNavController(R.id.navHostFragment)
                        .navigate(R.id.navLocationFragment, bundle)
            }
        }
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
