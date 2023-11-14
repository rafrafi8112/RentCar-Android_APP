package com.example.carbooking5.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbooking5.R;
import com.example.carbooking5.entities.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {

    private Context context;
    private List<Reservation> reservationList;

    private AdapterListener adapterListener;

    public ReservationAdapter(Context context, AdapterListener listener) {
        this.context = context;
        reservationList = new ArrayList<>();
        this.adapterListener= listener;
    }

    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
        notifyDataSetChanged();
    }

    public void removeReservation(int position){
        reservationList.remove(position);
        notifyDataSetChanged();
    }

    public void clearData(){
        reservationList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.customerName.setText(reservation.getCustomerName());
        holder.carModel.setText(reservation.getCarModel());
        holder.startDate.setText(reservation.getStartDate());
        holder.endDate.setText(reservation.getEndDate());
        // Utilisez une bibliothèque d'image comme Picasso ou Glide pour charger l'image depuis l'URL
        // Assurez-vous d'ajuster l'appel selon la structure réelle de votre modèle
        // Picasso.get().load(activity.getImageUrl()).into(holder.imageView);

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnUpdate(reservation);

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnDelete(reservation.getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView customerName , carModel, startDate , endDate;

        private ImageView update,delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.textViewName);
            carModel = itemView.findViewById(R.id.textViewCategory);
            startDate = itemView.findViewById(R.id.textViewDuration);
            endDate = itemView.findViewById(R.id.imageView);
            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
