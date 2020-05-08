package `in`.nitin.newsapp

import `in`.nitin.newsapp.ui.MainActivity
import `in`.nitin.newsapp.ui.NewsListFragmentDirections
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(manifest = Config.NONE, sdk = [android.os.Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class FragmentTest {

    lateinit var navController: NavController
    private var activity: MainActivity? = null

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get();
        navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)

    }


    @Test
    @Throws(Exception::class)
    fun actionTest() {
        val action =
            NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(
                "www.google.com",
                "Nitin"
            )
        assertNotNull(navController.navigate(action))
        withId(R.id.statusImageView).matches(isDisplayed())
        withId(R.id.webView).matches(isDisplayed())

    }


}