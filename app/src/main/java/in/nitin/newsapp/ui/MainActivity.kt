package `in`.nitin.newsapp.ui

import `in`.nitin.newsapp.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setToolbar(mTitle: String, isShowHome: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(isShowHome)
        supportActionBar!!.setHomeButtonEnabled(isShowHome)
        supportActionBar!!.title = mTitle
    }


    override fun onSupportNavigateUp(): Boolean {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}
