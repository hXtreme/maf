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

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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

        val currentRoot = router.backstack.firstOrNull()

        if (currentRoot?.tag()?.toIntOrNull() == id) {
            drawer_layout.closeDrawer(GravityCompat.START)
            return true
        }
        when (id) {
            R.id.nav_home -> setRoot(HomeController(), id)
            R.id.nav_rules -> setRoot(RulesController(), id)
            R.id.nav_stats -> setRoot(StatsController(), id)
            R.id.nav_share_stats -> setRoot(ShareStatisticsController(), id)
            R.id.nav_settings -> setRoot(SettingsController(), id)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setRoot(controller: Controller, id: Int) {
        router.setRoot(controller.withFadeTransaction().tag(id.toString()))
    }
}
