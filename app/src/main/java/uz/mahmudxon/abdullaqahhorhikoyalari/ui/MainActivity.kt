package uz.mahmudxon.abdullaqahhorhikoyalari.ui

import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.fragment_home.*
import uz.mahmudxon.abdullaqahhorhikoyalari.R
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onAfterCreate() {

    }

    override fun onBackPressed() {
        if (drawer_layout?.isDrawerOpen(GravityCompat.START) == true) {
            drawer_layout?.closeDrawer(GravityCompat.START)
            return
        }


        if (search_view?.isIconified == false) {
            search_view?.setQuery("", false)
            search_view?.isIconified = true
            return
        }

        super.onBackPressed()
    }
}

