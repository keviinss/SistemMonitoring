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
import com.example.skripsi.activity.mentor.EditPesanSantri;
import com.example.skripsi.activity.update.UpdateUserData;
import com.example.skripsi.model.PesanSantri;
import com.example.skripsi.model.Users;
import com.example.skripsi.services.MyPreferences;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterPesanSantri extends RecyclerView.Adapter<AdapterPesanSantri.MyViewHolder> {

    private Context context;
    private ArrayList<PesanSantri> pesanSantris;


    public AdapterPesanSantri(Context c, ArrayList<PesanSantri> pesanSantris) {
        this.context = c;
        this.pesanSantris = pesanSantris;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_pesan_santri, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final PesanSantri pesanSantri = this.pesanSantris.get(position);

        holder.txtLevel.setText(MyPreferences.getSharedPreferences()
                .getString(MyPreferences.LEVEL, "level"));

        String santri = holder.ttxtSantri.getText().toString();
        String mentor = holder.txtMentor.getText().toString();

        holder.txtSantri.setText(pesanSantri.getNamaSantri());
        holder.txtKodeSantri.setText(pesanSantri.getKodeSantri());
        holder.txtTgl.setText(pesanSantri.getTgl());
        holder.txtDeskripsi.setText(pesanSantri.getDeskripsi());

        if (holder.txtLevel.getText().equals("Santri") && holder.txtLevel.getText().equals("Mentor")) {
            holder.btnHapus.setVisibility(View.GONE);
            holder.btnUbah.setVisibility(View.GONE);
        } else {
            /*holder.btnHapus.setVisibility(View.VISIBLE);
            holder.btnUbah.setVisibility(View.VISIBLE);*/


            holder.btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("PesanSantri").child(pesanSantri.getUserId());
                    databaseReference.removeValue();
                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                }
            });

            holder.btnUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), EditPesanSantri.class);
                    i.putExtra("userId", pesanSantri.getUserId());
                    i.putExtra("namaSantri", pesanSantri.getNamaSantri());
                    i.putExtra("kodeSantri", pesanSantri.getKodeSantri());
                    i.putExtra("tgl", pesanSantri.getTgl());
                    i.putExtra("deskripsi", pesanSantri.getDeskripsi());
                    v.getContext().startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pesanSantris.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtSantri, txtKodeSantri, txtTgl, txtDeskripsi, txtLevel, ttxtSantri, txtMentor;
        Button btnHapus, btnUbah;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnHapus = itemView.findViewById(R.id.btnHapus);
            btnUbah = itemView.findViewById(R.id.btnUbah);
            txtKodeSantri = itemView.findViewById(R.id.txtKodeSantri);
            txtSantri = itemView.findViewById(R.id.txtNamaSantri);
            txtTgl = itemView.findViewById(R.id.txtTgl);
            txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            ttxtSantri = itemView.findViewById(R.id.ttxtSantri);
            txtMentor = itemView.findViewById(R.id.txtMentor);
        }
    }
}