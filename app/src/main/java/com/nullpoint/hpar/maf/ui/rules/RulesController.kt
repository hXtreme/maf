package com.nullpoint.hpar.maf.ui.rules

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nullpoint.hpar.maf.R
import com.nullpoint.hpar.maf.ui.base.controller.BaseController
import com.nullpoint.hpar.maf.ui.base.pager.FinitePagerAdapter

class RulesController : BaseController() {

    companion object {
        private val rulesSubsection = listOf<Pair<Int, Int>>(
            Pair(R.string.rules_gameplay, R.layout.rules_gameplay),
            Pair(R.string.rules_roles, R.layout.rules_roles)
        )
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.rules_controller, container, false)
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        val rulesPager = view.findViewById<ViewPager>(R.id.rules_pager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = FinitePagerAdapter(applicationContext!!, rulesSubsection)
        rulesPager.adapter = adapter
        tabs.setupWithViewPager(rulesPager)
    }

    override fun getTitle(): String? {
        return resources?.getString(R.string.rules_title)
    }
}