package com.example.assitancedistrubutionmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_Search extends AppCompatActivity {

    DatabaseReference mref;
    private ListView listViewSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__search);

        listViewSearch = findViewById(R.id.LIstUserDataSearch);
        mref = FirebaseDatabase.getInstance().getReference("Admin");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                readData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

    mref.addListenerForSingleValueEvent(eventListener);


    }

    private void readData(DataSnapshot snapshot) {

        if (snapshot.exists()) {
            ArrayList<String> listuser = new ArrayList<>();
            for(DataSnapshot ds : snapshot.getChildren()) {

                List list =  new List(ds.child("IDnumber").getValue(String.class),ds.child("FirstName").getValue(String.class),ds.child("MiddleName").getValue(String.class),ds.child("LastName").getValue(String.class),
                       ds.child("Gender").getValue(String.class),ds.child("Status").getValue(String.class),ds.child("TypeofAssitance").getValue(String.class),ds.child("Amount").getValue(String.class),ds.child("Date").getValue(String.class),ds.child("SponsoredBy").getValue(String.class));
                listuser.add(list.getFirstName()+"\n"+list.getMiddleName()+"\n"+list.getLastName()+"\n"+list.getGender()+"\n"+list.getStatus()+"\n"+list.getTypeofAssitance()+"\n"+list.getAmount()+"\n"
                +list.getDate()+"\n"+list.getSponsoredBy());


                }

            ArrayAdapter arrayAdapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1,listuser);
            listViewSearch.setAdapter(arrayAdapter);


            }
        else{
            Log.d("Admin","No Data Available");

        }
        }

    class List {
      public   String IDnumber, FirstName, MiddleName, LastName, Gender, Status, TypeofAssitance, Amount, Date, SponsoredBy;

        List(){


        }


        public List(String iDnumber,String firstName, String middleName, String lastName, String gender, String status, String type_of_Assitance, String amount, String date, String sponsored_by) {
            IDnumber = iDnumber;
            FirstName = firstName;
            MiddleName = middleName;
            LastName = lastName;
            Gender = gender;
            Status = status;
            TypeofAssitance = type_of_Assitance;
            Amount = amount;
            Date = date;
            SponsoredBy = sponsored_by;
        }

        public  String getIDnumber(){

            return  IDnumber;
        }


        public String getFirstName() {
            return FirstName;
        }

        public String getMiddleName() {
            return MiddleName;
        }

        public String getLastName() {
            return LastName;
        }



        public String getGender() {
            return Gender;
        }

        public String getStatus() {
            return Status;
        }

        public String getTypeofAssitance() {
            return TypeofAssitance;
        }

        public String getAmount() {
            return Amount;
        }

        public String getDate() {
            return Date;
        }

        public String getSponsoredBy() {
            return SponsoredBy;
        }
    }


}