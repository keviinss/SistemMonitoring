package com.example.skripsi.activity.retrieve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skripsi.R;
import com.example.skripsi.activity.RegisterActivity;
import com.example.skripsi.activity.mentor.InputPesanSantri;
import com.example.skripsi.adapter.AdapterPesanSantri;
import com.example.skripsi.adapter.AdapterUserData;
import com.example.skripsi.model.PesanSantri;
import com.example.skripsi.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrievePesanSantri extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<PesanSantri> pesanSantris;
    private AdapterPesanSantri adapterPesanSantri;
    DatabaseReference dRef;
    private SearchView searchView;
    ImageButton ImagebtnAdd;
    private ProgressBar mProgressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_pesan_santri);

        initView();

        ImagebtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InputPesanSantri.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mProgressCircle = findViewById(R.id.progress_circle);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pesanSantris = new ArrayList<PesanSantri>();
        ImagebtnAdd = findViewById(R.id.ImagebtnAdd);
        dRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (dRef != null) {
            Query query = dRef.child("PesanSantri");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        pesanSantris = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            pesanSantris.add(ds.getValue(PesanSantri.class));

                        }
                        adapterPesanSantri = new AdapterPesanSantri(RetrievePesanSantri.this, pesanSantris);
                        recyclerView.setAdapter(adapterPesanSantri);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(RetrievePesanSantri.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
        ArrayList<PesanSantri> myList = new ArrayList<>();
        for (PesanSantri object : pesanSantris)
        {
            if(object.getNamaSantri().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            else if(object.getKodeSantri().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            else if (object.getTgl().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            else if (object.getDeskripsi().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        adapterPesanSantri = new AdapterPesanSantri(RetrievePesanSantri.this, myList);
        recyclerView.setAdapter(adapterPesanSantri);
    }
}