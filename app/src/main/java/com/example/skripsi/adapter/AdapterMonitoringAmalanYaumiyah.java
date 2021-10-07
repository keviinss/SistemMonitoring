package com.example.skripsi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skripsi.R;
import com.example.skripsi.activity.update.UpdateUserData;
import com.example.skripsi.model.AmalanYaumiyahSantri;
import com.example.skripsi.model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterMonitoringAmalanYaumiyah extends RecyclerView.Adapter<AdapterMonitoringAmalanYaumiyah.MyViewHolder> {

    private Context context;
    private ArrayList<AmalanYaumiyahSantri> amalanYaumiyahSantris;


    public AdapterMonitoringAmalanYaumiyah(Context c, ArrayList<AmalanYaumiyahSantri> amalanYaumiyahSantris) {
        this.context = c;
        this.amalanYaumiyahSantris = amalanYaumiyahSantris ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_monitoring_amalanyaumiyah, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final AmalanYaumiyahSantri amalanYaumiyahSantri = this.amalanYaumiyahSantris.get(position);

            holder.txtnamaSantri.setText(amalanYaumiyahSantri.getNamasantri());
            holder.txtKodeSantri.setText(amalanYaumiyahSantri.getKodesantri());
            holder.txtTgl.setText(amalanYaumiyahSantri.getTglinput());
            holder.txtAmalanYaumiyah.setText(amalanYaumiyahSantri.getAmalanyaumiyah());

    }

    @Override
    public int getItemCount() {
        return amalanYaumiyahSantris.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtnamaSantri, txtTgl,txtAmalanYaumiyah,txtKodeSantri;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnamaSantri = itemView.findViewById(R.id.txtNamaSantri);
            txtTgl = itemView.findViewById(R.id.txtTgl);
            txtAmalanYaumiyah = itemView.findViewById(R.id.txtAmalan);
            txtKodeSantri = itemView.findViewById(R.id.txtKodeSantri);
        }
    }
}