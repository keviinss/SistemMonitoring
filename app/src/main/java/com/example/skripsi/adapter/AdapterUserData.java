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
import com.example.skripsi.model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterUserData extends RecyclerView.Adapter<AdapterUserData.MyViewHolder> {

    private Context context;
    private ArrayList<Users> users;


    public AdapterUserData(Context c, ArrayList<Users> users) {
        this.context = c;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_user_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Users userData = this.users.get(position);

            holder.txtUsername.setText(userData.getUsername());
            holder.txtPassword.setText(userData.getPassword());
            holder.txtNama.setText(userData.getNamaLengkap());
            holder.txtSantri.setText(userData.getNamaSantri());
            holder.txtKodeSantri.setText(userData.getKodeSantri());
            holder.txtNoHp.setText(userData.getNoHP());
            holder.txtLevel.setText(userData.getLevel());

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userData.getUserId());
                databaseReference.removeValue();
                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdateUserData.class);
                i.putExtra("userId", userData.getUserId());
                i.putExtra("username", userData.getUsername());
                i.putExtra("password", userData.getPassword());
                i.putExtra("namaLengkap", userData.getNamaLengkap());
                i.putExtra("namaSantri", userData.getNamaSantri());
                i.putExtra("kodeSantri", userData.getKodeSantri());
                i.putExtra("noHP", userData.getNoHP());
                i.putExtra("level", userData.getLevel());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsername, txtPassword, txtNama, txtNoHp, txtLevel,txtSantri,txtKodeSantri;
        CardView cardView;
        Button btnHapus, btnUbah;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtPassword = itemView.findViewById(R.id.txtPassword);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtNoHp = itemView.findViewById(R.id.txtNoHp);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            btnHapus = itemView.findViewById(R.id.btnHapus);
            btnUbah = itemView.findViewById(R.id.btnUbah);
            cardView = itemView.findViewById(R.id.cardView);
            txtKodeSantri = itemView.findViewById(R.id.txtKodeSantri);
            txtSantri = itemView.findViewById(R.id.txtNamaSantri);
        }
    }
}