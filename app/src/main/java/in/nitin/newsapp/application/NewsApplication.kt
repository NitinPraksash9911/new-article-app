package `in`.nitin.newsapp.application

import `in`.nitin.newsapp.di.ApplicationComponent
import `in`.nitin.newsapp.di.ApplicationModule
import `in`.nitin.newsapp.di.DaggerApplicationComponent
import `in`.nitin.newsapp.di.DatabaseModule
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        if (instance == null) {
            instance = this
        }

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .databaseModule(DatabaseModule(this))
            .build()

        applicationComponent.inject(this)
    }


    companion object {
        private var instance: NewsApplication? = null

        fun getNetworkStatus(): Boolean {
            return instance!!.isConnectionOn()
        }

        fun getComponent(): ApplicationComponent? {
            return instance!!.applicationComponent
        }
    }

    private fun isConnectionOn(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val connection = connectivityManager.getNetworkCapabilities(network)
            return connection != null && (
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            if (activeNetwork != null) {
                return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                        activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
            }
            return false
        }
    }

}