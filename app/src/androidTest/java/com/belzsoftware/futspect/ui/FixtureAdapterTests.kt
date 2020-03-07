package com.belzsoftware.futspect.ui

import android.content.Context
import android.view.View
import androidx.test.core.app.ApplicationProvider
import com.belzsoftware.futspect.ui.fixtures.infoVisibility
import com.belzsoftware.futspect.ui.fixtures.scoreVisibility
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FixtureAdapterTests {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun infoVisibility_NotStarted_ShowVs() {
        val view = View(context)

        infoVisibility(view, "NS")
        Assert.assertEquals(view.visibility, View.VISIBLE)
    }

    @Test
    fun infoVisibility_HalfTime_HideVs() {
        val view = View(context)

        infoVisibility(view, "HT")
        Assert.assertEquals(view.visibility, View.GONE)
    }

    @Test
    fun infoVisibility_FullTime_HideVs() {
        val view = View(context)

        infoVisibility(view, "FT")
        Assert.assertEquals(view.visibility, View.GONE)
    }

    @Test
    fun scoreVisibility_NotStarted_HideScore() {
        val view = View(context)

        scoreVisibility(view, "NS")
        Assert.assertEquals(view.visibility, View.GONE)
    }

    @Test
    fun scoreVisibility_HalfTime_ShowScore() {
        val view = View(context)

        scoreVisibility(view, "HT")
        Assert.assertEquals(view.visibility, View.VISIBLE)
    }

    @Test
    fun scoreVisibility_FullTime_ShowScore() {
        val view = View(context)

        scoreVisibility(view, "FT")
        Assert.assertEquals(view.visibility, View.VISIBLE)
    }
}