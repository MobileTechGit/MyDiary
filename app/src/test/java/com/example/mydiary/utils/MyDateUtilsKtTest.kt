package com.example.mydiary.utils

import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MyDateUtilsKtTest {

    @Before
    fun setUp() {
//        myDateUtils = Mockito.mock(MyDateUtils::class.java)
//        Mockito.`when`(myDateUtils.isToday(Date())).thenReturn(true)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isToday_Positive() {
        val date = Calendar.getInstance().time
        assertThat(MyDateUtils.isToday(date)).isTrue()
    }

    @Test
    fun isToday_Negative() {
        val date = Calendar.getInstance().also {
            it.set(
                it[Calendar.YEAR] - 1,
                it[Calendar.MONTH],
                it[Calendar.DATE]
            )
        }.time
        assertThat(MyDateUtils.isToday(date)).isFalse()
    }

    @Test
    fun isCurrentMonth_Negative() {
        val date = Calendar.getInstance().also {
            it.set(
                it[Calendar.YEAR] - 1,
                it[Calendar.MONTH],
                it[Calendar.DATE]
            )
        }.time
        assertThat(MyDateUtils.isToday(date)).isFalse()
    }

    @Test
    fun `display Feb 2020`() {
        val cal = Calendar.getInstance()
            .also { it.set(2020, 1, 15) }
        //This method will not runs in Test package because it contains android specific api for date format
        //It will run in androidTest package
        assertThat(MyDateUtils.getTimeThenDayThenMonth(cal.time)).matches("Feb 2020")
    }
}