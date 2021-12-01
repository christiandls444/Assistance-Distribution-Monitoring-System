package com.example.assitancedistrubutionmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListofAnnouncement extends AppCompatActivity {

        private RecyclerView ListofAnn;
        private FirebaseFirestore db = FirebaseFirestore.getInstance();
        private myAnnounceList myAnnounceList;
        private List<model> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_announcement);

        ListofAnn = findViewById(R.id.LisofAnnounceRec);
        ListofAnn.setHasFixedSize(true);
        ListofAnn.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAnnounceList = new myAnnounceList(this,list);
        ListofAnn.setAdapter(myAnnounceList);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper(myAnnounceList));
        touchHelper.attachToRecyclerView(ListofAnn);




        showList();



    }

    public void showList() {

        db.collection("Announcement").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot :task.getResult()){
                    model Model = new model(snapshot.getString("id"),snapshot.getString("Tittle"),snapshot.getString("Message"),snapshot.getString("DatePost"),snapshot.getString("TimePost"));
                    list.add(Model);
                }
                myAnnounceList.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ListofAnnouncement.this,"Something went error!",Toast.LENGTH_LONG).show();
            }
        });

    }
}