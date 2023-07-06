package com.damai.base

import com.damai.base.util.SimpleDateUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.util.Calendar

/**
 * Created by damai007 on 06/July/2023
 */
class SimpleDateUtilTest {

    @Test
    fun `parse date to string`() {
        val calendar = Calendar.getInstance()
        calendar.set(2023, 5, 7)
        val givenDate = calendar.time
        val outputFormat = SimpleDateUtil.DateFormat.DD_MM_YYYY

        val result = SimpleDateUtil.parseDateToString(
            givenDate = givenDate,
            outputFormat = outputFormat
        )

        assertEquals("07-06-2023", result)
    }

    @Test
    fun `parse string to date`() {
        val givenDateString = "07-06-2023"
        val sourceFormat = SimpleDateUtil.DateFormat.DD_MM_YYYY

        val result = SimpleDateUtil.parseStringToDate(
            givenDateString = givenDateString,
            sourceFormat = sourceFormat
        )
        val calendar = Calendar.getInstance()
        calendar.time = requireNotNull(result)

        assertEquals(2023, calendar.get(Calendar.YEAR))
        assertEquals(5, calendar.get(Calendar.MONTH))
        assertEquals(7, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun `parse string to date but wrong format`() {
        val givenDateString = "07-06-2023"
        val sourceFormat = SimpleDateUtil.DateFormat.YYYY_MM_DD

        val result = SimpleDateUtil.parseStringToDate(
            givenDateString = givenDateString,
            sourceFormat = sourceFormat
        )
        val calendar = Calendar.getInstance()
        calendar.time = requireNotNull(result)

        assertNotEquals(2023, calendar.get(Calendar.YEAR))
        assertNotEquals(5, calendar.get(Calendar.MONTH))
        assertNotEquals(7, calendar.get(Calendar.DAY_OF_MONTH))
    }
}