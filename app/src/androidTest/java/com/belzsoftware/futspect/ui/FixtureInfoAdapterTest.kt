package com.belzsoftware.futspect.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Before

class FixtureInfoAdapterTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

//    @Test
//    fun setFixtureDatetime() {
//        val view = MaterialTextView(context)
//        val date = java.util.Calendar.se
//
//        setFixtureDatetime(view, date)
//        Assert.assertEquals(view.visibility, View.VISIBLE)
//    }
}