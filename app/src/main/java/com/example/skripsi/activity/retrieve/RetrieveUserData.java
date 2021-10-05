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
import com.example.skripsi.adapter.AdapterUserData;
import com.example.skripsi.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrieveUserData extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Users> users;
    private AdapterUserData adapterUserData;
    DatabaseReference dRef;
    private SearchView searchView;
    ImageButton ImagebtnAdd;
    private ProgressBar mProgressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_user_data);

        initView();

        ImagebtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mProgressCircle = findViewById(R.id.progress_circle);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        users = new ArrayList<Users>();
        ImagebtnAdd = findViewById(R.id.ImagebtnAdd);
        dRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (dRef != null) {
            Query query = dRef.child("Users");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        users = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            users.add(ds.getValue(Users.class));

                        }
                        adapterUserData = new AdapterUserData(RetrieveUserData.this, users);
                        recyclerView.setAdapter(adapterUserData);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(RetrieveUserData.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
        ArrayList<Users> myList = new ArrayList<>();
        for (Users object : users)
        {
            if(object.getUsername().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            else if(object.getNamaLengkap().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            else if (object.getNoHP().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            else if (object.getLevel().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }
        adapterUserData = new AdapterUserData(RetrieveUserData.this, myList);
        recyclerView.setAdapter(adapterUserData);
    }
}