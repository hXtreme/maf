package com.nullpoint.hpar.maf

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.bluelinelabs.conductor.*
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.ViewGroup
import com.nullpoint.hpar.maf.ui.base.controller.withFadeTransaction
import com.nullpoint.hpar.maf.ui.home.HomeController
import com.nullpoint.hpar.maf.ui.rules.RulesController
import com.nullpoint.hpar.maf.ui.settings.SettingsController
import com.nullpoint.hpar.maf.ui.share_stats.ShareStatisticsController
import com.nullpoint.hpar.maf.ui.stats.StatsController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val container: ViewGroup = findViewById(R.id.content_main)
        router = Conductor.attachRouter(this, container, savedInstanceState)
        router.setRoot(HomeController().withFadeTransaction().tag(R.id.nav_home.toString()))

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers()
            return
        }

        val backstackSize = router.backstackSize
        when {
            backstackSize > 2 -> router.handleBack()

            backstackSize == 2 -> {
                setSelectedDrawerItem(R.id.nav_home)
                router.handleBack()
            }

            else -> super.onBackPressed()
        }
    }

    private fun setSelectedDrawerItem(itemId: Int) {
        if (!isFinishing) {
            nav_view.setCheckedItem(itemId)
            //nav_view.menu.performIdentifierAction(itemId, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // TODO: Remove: menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            // TODO: Remove: R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        val currentController = router.backstack.lastOrNull()

        if (currentController?.tag()?.toIntOrNull() == id) {
            drawer_layout.closeDrawer(GravityCompat.START)
            return true
        }
        when (id) {
            R.id.nav_home -> router.popToRoot()
            R.id.nav_rules -> navigateTo(RulesController(), id, true)
            R.id.nav_stats -> navigateTo(StatsController(), id, true)
            R.id.nav_share_stats -> navigateTo(ShareStatisticsController(), id, true)
            R.id.nav_settings -> navigateTo(SettingsController(), id, true)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun navigateTo(controller: Controller, id: Int, secondInStack: Boolean = false) {
        if (secondInStack) {
            router.popToRoot()
        }
        router.pushController(controller.withFadeTransaction().tag(id.toString()))
    }
}
