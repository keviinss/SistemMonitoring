package com.example.skripsi.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skripsi.R;
import com.example.skripsi.activity.update.UpdateUserData;
import com.example.skripsi.model.Donasi;
import com.example.skripsi.model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterDonasi extends RecyclerView.Adapter<AdapterDonasi.MyViewHolder> {

    private Context context;
    private ArrayList<Donasi> users;

    public AdapterDonasi(Context c, ArrayList<Donasi> users) {
        this.context = c;
        this.users = users;
    }

    @NonNull
    @Override
    public AdapterDonasi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_donasi, parent, false);
        return new AdapterDonasi.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDonasi.MyViewHolder holder, int position) {

        final Donasi donasi = this.users.get(position);

        holder.Nama.setText(donasi.getNama());
        holder.Tanggal.setText(donasi.getTanggal());
        holder.Jumlah.setText(donasi.getJumlah());
        holder.Metode.setText(donasi.getMetode());

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Donasi").child(donasi.getDonasiId());
                databaseReference.removeValue();
                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Nama, Tanggal, Jumlah, Metode;
        CardView cardView;
        Button btnHapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Nama = itemView.findViewById(R.id.Nama);
            Tanggal = itemView.findViewById(R.id.Tanggal);
            Jumlah = itemView.findViewById(R.id.Jumlah);
            Jumlah = itemView.findViewById(R.id.Jumlah);
            Metode = itemView.findViewById(R.id.Metode);
            btnHapus = itemView.findViewById(R.id.btnHapus);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}