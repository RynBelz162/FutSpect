package com.belzsoftware.futspect.ui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.core.app.ApplicationProvider
import com.belzsoftware.futspect.R
import com.belzsoftware.futspect.ui.leagues.table.rankColor
import com.belzsoftware.futspect.util.extensions.getAttrColor
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TableAdapterTests {

    private lateinit var context: Context

    @Before
    fun setUpTheme() {
        context = ApplicationProvider.getApplicationContext()
        context.setTheme(R.style.Theme_FutSpect)
    }

    @Test
    fun rankColor_nullDescription_setsDefault() {
        val view = View(context)
        rankColor(view, null)

        val expectedColor = getAttrColor(context, R.attr.colorSurface)
        val bgColor = view.background as ColorDrawable
        Assert.assertEquals(expectedColor, bgColor.color)
    }

    @Test
    fun rankColor_promotionDescription_setsOkay() {
        val view = View(context)
        rankColor(view, "Promotion is super awesome")

        val expectedColor = getAttrColor(context, R.attr.colorOkay)
        val bgColor = view.background as ColorDrawable
        Assert.assertEquals(expectedColor, bgColor.color)
    }

    @Test
    fun rankColor_relegationDescription_setsError() {
        val view = View(context)
        rankColor(view, "Relegation is super lame")

        val expectedColor = getAttrColor(context, R.attr.colorError)
        val bgColor = view.background as ColorDrawable
        Assert.assertEquals(expectedColor, bgColor.color)
    }
}