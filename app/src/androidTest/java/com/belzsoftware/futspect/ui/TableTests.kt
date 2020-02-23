package com.belzsoftware.futspect.ui

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.core.app.ApplicationProvider
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.ui.leagues.table.rankColor
import org.junit.Test

// TODO: Fix these tests, asserts not working?
class TableTests {
    @Test
    fun rankColor_nullDescription_setsDefault() {
        val view = View(ApplicationProvider.getApplicationContext())
        rankColor(view, null)

        val bgColor = view.background as ColorDrawable
        assert(bgColor.color == R.color.design_default_color_surface)
    }

    @Test
    fun rankColor_promotionDescription_setsOkay() {
        val view = View(ApplicationProvider.getApplicationContext())
        rankColor(view, "Promotion is super awesome")

        val bgColor = view.background as ColorDrawable
        assert(bgColor.color == R.color.material_green_500)
    }

    @Test
    fun rankColor_relegationDescription_setsError() {
        val view = View(ApplicationProvider.getApplicationContext())
        rankColor(view, "Relegation is super lame")

        val bgColor = view.background as ColorDrawable
        assert(bgColor.color == R.color.design_default_color_error)
    }
}