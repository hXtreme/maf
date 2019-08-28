package com.nullpoint.hpar.maf.ui.base.pager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * PagerAdapter for finitely many pages
 * @param context Context
 * @param pages List of Pairs of Resource Id of Title and Layout of pages.
 */
class FinitePagerAdapter(
    private val context: Context,
    private val pages: List<Pair<Int, Int>>
) :
    BasePagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(
            pages[position].second, container, false
        )
        container.addView(view, position)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(pages[position].first)
    }
}