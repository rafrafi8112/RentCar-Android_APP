package com.example.carbooking5.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.carbooking5.dao.ReservationDao;
import com.example.carbooking5.entities.Reservation;

@Database(entities ={Reservation.class} ,version =1 ,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ReservationDao reservationDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ReservationDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return INSTANCE;
    }
}
