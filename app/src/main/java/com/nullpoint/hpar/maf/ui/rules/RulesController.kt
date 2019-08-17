package com.nullpoint.hpar.maf.ui.rules

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nullpoint.hpar.maf.R
import com.nullpoint.hpar.maf.ui.base.controller.BaseController

class RulesController : BaseController() {


    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.rules_controller, container, false)
    }

    override fun getTitle(): String? {
        return resources?.getString(R.string.rules_title)
    }
}