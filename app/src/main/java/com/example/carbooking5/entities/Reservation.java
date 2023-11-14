package com.example.carbooking5.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Reservation implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public  int id ;
    @ColumnInfo
    private String carModel;
    @ColumnInfo
    private String customerName;
    @ColumnInfo
    private String startDate;
    @ColumnInfo
    private String endDate;


    public int getId() {
        return id;
    }

    /* GETTERS */
    public String getCustomerName() {
        return customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }


    /* SETTERS */
    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;

    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }



    /* CONSTRUCTOR */

    public Reservation(int id, String carModel, String customerName, String startDate, String endDate) {
        this.id = id;
        this.carModel = carModel;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation() {
    }

    public Reservation(String customerName, String carModel, String startDate, String endDate) {
        this.customerName = customerName;
        this.carModel = carModel;
        this.startDate = startDate;
        this.endDate = endDate;

    }
}