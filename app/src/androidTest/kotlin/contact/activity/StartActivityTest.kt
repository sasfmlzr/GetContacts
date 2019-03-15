package contact.activity

import android.content.Context
import android.net.ConnectivityManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import contact.R
import contact.activity.start.StartActivity
import org.junit.Rule
import org.junit.Test

class StartActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(StartActivity::class.java)

    @Test
    fun verify_Start_Activity() {
        onView(withId(R.id.firstInitText)).check(matches(withText("Initialization")))

        if (isConnected(activityTestRule.activity)) {
            onView(withId(R.id.buttonRetry))
                    .check(matches(withEffectiveVisibility(Visibility.GONE)))
            Thread.sleep(2000)
            onView(withId(R.id.recycler_view_owner_contacts))
                    .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        } else {
            Thread.sleep(2000)
            onView(withId(R.id.buttonRetry))
                    .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            onView(withId(com.google.android.material.R.id.snackbar_text))
                    .check(matches(withText("Server not response")))
        }
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
