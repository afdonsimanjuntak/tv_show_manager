package io.afdon.tvshowmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import io.afdon.home.ui.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = (application as TvShowManagerApplication)
            .appComponent
            .getActivitySubcomponentFactory()
            .create(this@MainActivity)
            .getFragmentFactory()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace(android.R.id.content, HomeFragment::class.java, null)
            addToBackStack(HomeFragment::class.simpleName)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            moveTaskToBack(true)
        } else {
            super.onBackPressed()
        }
    }
}