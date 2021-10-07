package com.example.skripsi.activity.retrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.skripsi.R;
import com.example.skripsi.activity.RegisterActivity;
import com.example.skripsi.activity.ortu.TambahDonasi;
import com.example.skripsi.adapter.AdapterDonasi;
import com.example.skripsi.adapter.AdapterUserData;
import com.example.skripsi.model.Donasi;
import com.example.skripsi.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrieveDonasi extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Donasi> donasi;
    private AdapterDonasi adapterDonasi;
    DatabaseReference dRef;
    private SearchView searchView;
    ImageButton ImagebtnAdd;
    private ProgressBar mProgressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_donasi);

        initView();

        ImagebtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TambahDonasi.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mProgressCircle = findViewById(R.id.progress_circle);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        donasi = new ArrayList<Donasi>();
        ImagebtnAdd = findViewById(R.id.ImagebtnAdd);
        dRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (dRef != null) {
            Query query = dRef.child("Donasi");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        donasi = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            donasi.add(ds.getValue(Donasi.class));

                        }
                        adapterDonasi = new AdapterDonasi(RetrieveDonasi.this, donasi);
                        recyclerView.setAdapter(adapterDonasi);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(RetrieveDonasi.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    mProgressCircle.setVisibility(View.INVISIBLE);
                }
            });
        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str)
    {
        ArrayList<Donasi> myList = new ArrayList<>();
        for (Donasi object : donasi)
        {
            if(object.getNama().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            else if(object.getJumlah().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            else if(object.getTanggal().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        adapterDonasi = new AdapterDonasi(RetrieveDonasi.this, myList);
        recyclerView.setAdapter(adapterDonasi);
    }
}