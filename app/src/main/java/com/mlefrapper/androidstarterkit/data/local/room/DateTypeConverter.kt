package com.mlefrapper.androidstarterkit.data.local.room

import androidx.room.TypeConverter
import com.mlefrapper.androidstarterkit.utils.ConverterDate
import com.mlefrapper.androidstarterkit.utils.toDate
import com.mlefrapper.androidstarterkit.utils.toString
import java.util.Date

class DateTypeConverter {

    @TypeConverter
    fun fromDateToString(date: Date?): String? {
        return date?.toString(ConverterDate.SQL_DATE)
    }

    @TypeConverter
    fun fromStringToDate(dateString: String?): Date? {
        return dateString?.toDate()
    }
}
