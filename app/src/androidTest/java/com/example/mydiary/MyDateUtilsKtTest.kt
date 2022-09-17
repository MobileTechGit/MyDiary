package com.example.mydiary

import com.example.mydiary.utils.MyDateUtils
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class MyDateUtilsKtTest {

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
    }

    @Test
    fun displayDate_LessThanOneHour_ReturnsTrue() {
        val sampleDate = Calendar.getInstance()
            .also { it.add(Calendar.MINUTE, -2) }.time
        //This method runs in androidTest package because it contains android specific api for date format
        assertThat(MyDateUtils.getTimeThenDayThenMonth(sampleDate))
            .matches("2 minutes ago")
    }
}