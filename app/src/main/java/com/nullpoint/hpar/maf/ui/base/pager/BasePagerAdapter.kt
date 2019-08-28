package com.nullpoint.hpar.maf.ui.base.pager

import android.view.View
import androidx.viewpager.widget.PagerAdapter

abstract class BasePagerAdapter() : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}