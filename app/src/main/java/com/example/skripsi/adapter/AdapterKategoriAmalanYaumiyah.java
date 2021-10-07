package com.example.skripsi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skripsi.R;
import com.example.skripsi.model.KategoriAmalanYaumiyah;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterKategoriAmalanYaumiyah extends RecyclerView.Adapter<AdapterKategoriAmalanYaumiyah.MyViewHolder> {

    private Context context;
    private ArrayList<KategoriAmalanYaumiyah> kategoriAmalanYaumiyahs;


    public AdapterKategoriAmalanYaumiyah(Context c, ArrayList<KategoriAmalanYaumiyah> kategoriAmalanYaumiyahs) {
        this.context = c;
        this.kategoriAmalanYaumiyahs = kategoriAmalanYaumiyahs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_amalanyaumiyah, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final KategoriAmalanYaumiyah kategoriAmalanYaumiyah = this.kategoriAmalanYaumiyahs.get(position);

            holder.txtAmalanYaumiyah.setText(kategoriAmalanYaumiyah.getAmalanYaumiyah());

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AmalanYaumiyah").child(kategoriAmalanYaumiyah.getUserId());
                databaseReference.removeValue();
                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return kategoriAmalanYaumiyahs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtAmalanYaumiyah;
        Button btnHapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAmalanYaumiyah = itemView.findViewById(R.id.txtAmalanYaumiyah);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}