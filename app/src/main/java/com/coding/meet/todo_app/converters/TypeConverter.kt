package com.coding.meet.todo_app.converters

import androidx.room.TypeConverter
import java.util.Date

// Custom TypeConverter class for converting Date objects to and from Long timestamps
class TypeConverter {

    // Convert Long timestamp to Date object
    @TypeConverter
    fun fromTimestamp(value:Long): Date {
        return Date(value)
    }

    // Convert Date object to Long timestamp
    @TypeConverter
    fun dateToTimestamp(date:Date): Long {
        return date.time
    }
}