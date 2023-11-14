package com.example.carbooking5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carbooking5.dao.AdapterListener;
import com.example.carbooking5.dao.ReservationAdapter;
import com.example.carbooking5.dao.ReservationDao;
import com.example.carbooking5.database.AppDatabase;
import com.example.carbooking5.entities.Reservation;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener {
    EditText customerNameRes, carModelRes, startDateRes, endDateRes;
    Button addBtn;
    RecyclerView recyclerViewAct;

    private AppDatabase appDatabase;
    private ReservationDao reservationDao;
    private ReservationAdapter reservationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = AppDatabase.getInstance(this);
        reservationDao = appDatabase.reservationDao();

        customerNameRes = findViewById(R.id.editTextName);
        carModelRes = findViewById(R.id.editTextCategory);
        startDateRes = findViewById(R.id.editTextDuration);
        endDateRes = findViewById(R.id.editTextImageUrl);
        addBtn = findViewById(R.id.buttonSave);
        recyclerViewAct = findViewById(R.id.recyclerView);

        reservationAdapter = new ReservationAdapter(this,this);
        recyclerViewAct.setAdapter(reservationAdapter);
        recyclerViewAct.setLayoutManager(new LinearLayoutManager(this));

        //fetchData();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = customerNameRes.getText().toString();
                String carModel = carModelRes.getText().toString();
                String startDate = startDateRes.getText().toString();
                String endDate = endDateRes.getText().toString();

                Reservation reservation = new Reservation( customerName , carModel , startDate, endDate);
                reservationAdapter.addReservation(reservation);
                reservationDao.insertReservation(reservation);

                customerNameRes.setText("");
                carModelRes.setText("");
                startDateRes.setText("");
                endDateRes.setText("");
                Toast.makeText(MainActivity.this, "Résérvation ajoutée avec succès", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchData() {
        reservationAdapter.clearData();
        List<Reservation> reservationList = reservationDao.getAllReservations();
        for (int i=0;i<reservationList.size();i++) {
            Reservation reservation = reservationList.get(i);
            reservationAdapter.addReservation(reservation);
        }
    }


    @Override
    public void OnUpdate(Reservation reservation) {
        Intent intent = new Intent(this, UpdateReservation.class);
        intent.putExtra("model",reservation);
        startActivity(intent);
    }


    @Override
    public void OnDelete(int id, int pos) {
        reservationDao.deleteReservation(id);
        reservationAdapter.removeReservation(pos);
        Toast.makeText(MainActivity.this, "Résérvation supprimée avec succès", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

}