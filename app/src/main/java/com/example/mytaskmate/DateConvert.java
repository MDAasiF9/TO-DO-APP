package com.example.mytaskmate;

import androidx.room.TypeConverter;
import java.util.Date;

public class DateConvert {
@TypeConverter
    public static Date toDate(Long timeSatamp){
    return timeSatamp == null ? null : new Date(timeSatamp);
}
@TypeConverter
    public static Long toTimeStamp(Date date){
    return date == null ? null : date.getTime();
}
}
