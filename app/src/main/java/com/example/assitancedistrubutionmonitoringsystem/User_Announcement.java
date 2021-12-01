package com.example.assitancedistrubutionmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class User_Announcement extends AppCompatActivity {
    private RecyclerView ListofAnnUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private myCoat mycoat;
    private List<coat> coat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__announcement);

        ListofAnnUser = findViewById(R.id.UserAnn_Recycle);
        ListofAnnUser.setHasFixedSize(true);
        ListofAnnUser.setLayoutManager(new LinearLayoutManager(this));

        coat = new ArrayList<>();
        mycoat = new myCoat(this,coat);
        ListofAnnUser.setAdapter(mycoat);

        showList();





    }

    private void showList() {

        db.collection("Announcement").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                coat.clear();
                for (DocumentSnapshot snapshot :task.getResult()){

                    coat Coat = new coat(snapshot.getString("id"),snapshot.getString("Tittle"),snapshot.getString("Message"),snapshot.getString("DatePost"),snapshot.getString("TimePost"));
                    coat.add(Coat);


                }
                mycoat.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(User_Announcement.this,"Something went error!",Toast.LENGTH_LONG).show();
            }
        });

    }
    }
