package com.example.skripsi.activity.retrieve;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skripsi.R;
import com.example.skripsi.adapter.AdapterKategoriAmalanYaumiyah;
import com.example.skripsi.model.KategoriAmalanYaumiyah;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrieveKategoriAmalanYaumiyah extends AppCompatActivity {
    private RecyclerView recyclerView;
    DatabaseReference dRef;
    private SearchView searchView;
    private ArrayList<KategoriAmalanYaumiyah> kategoriAmalanYaumiyahs;
    private AdapterKategoriAmalanYaumiyah adapterKategoriAmalanYaumiyah;
    private ProgressBar mProgressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_kategori_amalanyaumiyah);

        initView();

    }

    private void initView() {
        mProgressCircle = findViewById(R.id.progress_circle);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (dRef != null) {
            Query query = dRef.child("AmalanYaumiyah");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        kategoriAmalanYaumiyahs = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            kategoriAmalanYaumiyahs.add(ds.getValue(KategoriAmalanYaumiyah.class));
                        }
                        adapterKategoriAmalanYaumiyah = new AdapterKategoriAmalanYaumiyah(RetrieveKategoriAmalanYaumiyah.this, kategoriAmalanYaumiyahs);
                        recyclerView.setAdapter(adapterKategoriAmalanYaumiyah);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(RetrieveKategoriAmalanYaumiyah.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
        ArrayList<KategoriAmalanYaumiyah> myList = new ArrayList<>();
        for (KategoriAmalanYaumiyah object : kategoriAmalanYaumiyahs)
        {
            if(object.getAmalanYaumiyah().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        adapterKategoriAmalanYaumiyah = new AdapterKategoriAmalanYaumiyah(RetrieveKategoriAmalanYaumiyah.this, myList);
        recyclerView.setAdapter(adapterKategoriAmalanYaumiyah);
    }
}