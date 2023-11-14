package com.example.carbooking5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carbooking5.dao.ReservationDao;
import com.example.carbooking5.database.AppDatabase;
import com.example.carbooking5.entities.Reservation;

public class UpdateReservation extends AppCompatActivity {

    EditText customerNameRes , carModelRes , startDateRes , endDateRes ;
    Button updateBtn;
    private Reservation reservation;
    private AppDatabase appDatabase;
    private ReservationDao reservationDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_update);

        appDatabase = AppDatabase.getInstance(this);
        reservationDao = appDatabase.reservationDao();

        customerNameRes = findViewById(R.id.editTextName);
        carModelRes = findViewById(R.id.editTextCategory);
        startDateRes = findViewById(R.id.editTextDuration);
        endDateRes = findViewById(R.id.editTextImageUrl);
        updateBtn = findViewById(R.id.buttonEdit);

        reservation = (Reservation) getIntent().getSerializableExtra("model");

        customerNameRes.setText(reservation.getCustomerName());
        carModelRes.setText(reservation.getCarModel());
        startDateRes.setText(reservation.getStartDate());
        endDateRes.setText(reservation.getEndDate());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reservation activityModel = new Reservation(
                        reservation.getId(),
                        customerNameRes.getText().toString(),
                        carModelRes.getText().toString(),
                        endDateRes.getText().toString(),
                        endDateRes.getText().toString()) ;
               reservationDao.updateReservation(activityModel);
               finish();
            }
        });
    }
}