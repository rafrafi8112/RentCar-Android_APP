package com.example.carbooking5.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.carbooking5.entities.Reservation;

import java.util.List;

@Dao
public interface ReservationDao {

    @Insert
    void insertReservation(Reservation reservation);

    @Update
    void updateReservation(Reservation reservation);

    @Query("delete from Reservation where id=:id")
    void deleteReservation(int id);

    @Query("Select * from Reservation")
    List<Reservation> getAllReservations();


}
