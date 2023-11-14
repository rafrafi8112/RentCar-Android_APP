package com.example.carbooking5.dao;

import com.example.carbooking5.entities.Reservation;

public interface AdapterListener {

    void OnUpdate(Reservation activity);
    void OnDelete(int id, int pos);
}
