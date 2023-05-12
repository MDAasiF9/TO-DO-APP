package com.example.mytaskmate;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = Note.class, version = 5)
@TypeConverters({DateConvert.class, TimeConvert.class})
public abstract class NoteDB extends RoomDatabase {

    private static NoteDB instance;

    public abstract NoteDao noteDao();
    public static synchronized NoteDB getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,NoteDB.class,"myTodo_notes").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
