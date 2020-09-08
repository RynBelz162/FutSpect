package com.belzsoftware.futspect.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.belzsoftware.futspect.model.fixture.Venue
import com.belzsoftware.futspect.ui.fixtures.info.setFixtureDatetime
import com.belzsoftware.futspect.ui.fixtures.info.setVenue
import com.google.android.material.textview.MaterialTextView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month

class FixtureInfoAdapterTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun setFixtureDatetime_FebSixteen_ShowsFormattedDate() {
        val view = MaterialTextView(context)
        val date = LocalDateTime.of(2050, Month.FEBRUARY, 16, 12, 0)

        setFixtureDatetime(view, date)
        Assert.assertEquals("Wed Feb 16, 2050. 12:00 PM", view.text)
    }

    @Test
    fun setVenue_NameAndCity_ShowsBothFormatted() {
        val view = MaterialTextView(context)
        val venue = Venue(1000, "Emirates Stadium", "London")

        setVenue(view, venue)
        Assert.assertEquals("Emirates Stadium, London", view.text)
    }

    @Test
    fun setVenue_OnlyName_ShowsOnlyNameNoComma() {
        val view = MaterialTextView(context)
        val venue = Venue(1000, "Emirates Stadium", "")

        setVenue(view, venue)
        Assert.assertEquals("Emirates Stadium", view.text)
    }
}