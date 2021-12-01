package com.example.assitancedistrubutionmonitoringsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class EditAndDeleteInfo extends AppCompatActivity {

    RecyclerView Listreciepv;
    myadapter myadapter;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_and_delete_info);
        Listreciepv = findViewById(R.id.Edit_and_Deleteview);
        Listreciepv.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<adapter> options =
                new FirebaseRecyclerOptions.Builder<adapter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Admin"), adapter.class)
                        .build();


        myadapter =  new myadapter(options);
        Listreciepv.setAdapter(myadapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        myadapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        myadapter.stopListening();
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
        FirebaseRecyclerOptions<adapter> options =
                new FirebaseRecyclerOptions.Builder<adapter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Admin").orderByChild("IDnumber").startAt(query)
                                .endAt(query+"\uf8ff"), adapter.class)
                        .build();




        myadapter =  new myadapter(options);
        myadapter.startListening();
        Listreciepv.setAdapter(myadapter);

    }
}