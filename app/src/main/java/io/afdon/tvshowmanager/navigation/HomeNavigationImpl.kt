package io.afdon.tvshowmanager.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import io.afdon.add.ui.AddTvShowFragment
import io.afdon.home.nav.HomeNavigation
import io.afdon.list.ui.TvShowsFragment
import javax.inject.Inject

class HomeNavigationImpl @Inject constructor(
    private val activity: AppCompatActivity
) : HomeNavigation {

    override fun openAddTvShowAddForm() {
        activity.supportFragmentManager.commit {
            replace(android.R.id.content, AddTvShowFragment::class.java, null)
            addToBackStack(AddTvShowFragment::class.simpleName)
        }
    }

    override fun openTvShowList() {
        activity.supportFragmentManager.commit {
            replace(android.R.id.content, TvShowsFragment::class.java, null)
            addToBackStack(TvShowsFragment::class.simpleName)
        }
    }
}