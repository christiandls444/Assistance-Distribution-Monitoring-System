package com.example.assitancedistrubutionmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListofReciepientInterface extends AppCompatActivity {
    RecyclerView Listreciepv;
    mylist mylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_reciepient_interface);
        Listreciepv = findViewById(R.id.ListofRecieptview);
        Listreciepv.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<List> options =
                new FirebaseRecyclerOptions.Builder<List>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Admin"), List.class)
                        .build();


        mylist =  new mylist(options);
        Listreciepv.setAdapter(mylist);

    }



    @Override
    protected void onStart() {
        super.onStart();
        mylist.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mylist.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item =  menu.findItem(R.id.search);

        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                processSearch(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                processSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);



    }

    private void processSearch(String query) {

        FirebaseRecyclerOptions<List> options =
                new FirebaseRecyclerOptions.Builder<List>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Admin").orderByChild("IDnumber").startAt(query)
                                .endAt(query+"\uf8ff"), List.class)
                        .build();

        mylist =  new mylist(options);
        mylist.startListening();
        Listreciepv.setAdapter(mylist);

    }
}
