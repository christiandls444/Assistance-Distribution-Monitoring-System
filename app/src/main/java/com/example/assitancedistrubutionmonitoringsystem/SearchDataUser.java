package com.example.assitancedistrubutionmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchDataUser extends AppCompatActivity {

    DatabaseReference mref;
    private ListView ListUserData;
    private AutoCompleteTextView SearchLName;
    ArrayAdapter<List> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_data_user);
        mref = FirebaseDatabase.getInstance().getReference("Admin");
        ListUserData = findViewById(R.id.searchListDataUSer);
        SearchLName = findViewById(R.id.SearchUserData);


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                populateSearch(snapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mref.addListenerForSingleValueEvent(eventListener);

    }
    private void populateSearch(DataSnapshot snapshot) {

        Log.d("Admin","Reading Data");
        ArrayList<String> Lname = new ArrayList<>();
        if (snapshot.exists()){

            for(DataSnapshot ds : snapshot.getChildren()) {

                String name = ds.child("IDnumber").getValue(String.class);
                Lname.add(name);

            }

            ArrayAdapter arrayAdapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1,Lname);
            SearchLName.setAdapter(arrayAdapter);

            SearchLName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String name = SearchLName.getText().toString();
                    SearchLuser(name);
                }
            });

        }else{

            Log.d("Admin","No Data Available");
        }





    }

    private void SearchLuser(String name) {


        Query query = mref.orderByChild("IDnumber").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<String> listuser = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        ListD listd = new ListD(ds.child("IDnumber").getValue(String.class), ds.child("FirstName").getValue(String.class), ds.child("MiddleName").getValue(String.class), ds.child("LastName").getValue(String.class),
                                ds.child("Gender").getValue(String.class), ds.child("Status").getValue(String.class), ds.child("TypeofAssitance").getValue(String.class), ds.child("Amount").getValue(String.class), ds.child("Date").getValue(String.class), ds.child("SponsoredBy").getValue(String.class)
                                , ds.child("NotedBy").getValue(String.class), ds.child("NoteDate").getValue(String.class), ds.child("Time").getValue(String.class));


                        listuser.add(listd.getFirstName() + "\n" + listd.getMiddleName() + "\n" + listd.getLastName() + "\n" + listd.getGender() + "\n" + listd.getStatus() + "\n" + listd.getTypeofAssitance() + "\n" + listd.getAmount() + "\n"
                                + listd.getDate() + "\n" + listd.getSponsoredBy()+ "\n" + listd.getNotedBy()+ "\n" + listd.getNoteDate()+ "\n" + listd.getTime());
                    }

                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.usersearchtextlist, listuser);
                    ListUserData.setAdapter(arrayAdapter);


                } else {
                    Log.d("Admin", "No Data Found");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    }
class ListD {
    public   String IDnumber, FirstName, MiddleName, LastName, Gender, Status, TypeofAssitance, Amount, Date, SponsoredBy,NotedBy,
            NoteDate,Time;

    ListD(){


    }


    public ListD(String iDnumber,String firstName, String middleName, String lastName, String gender, String status, String type_of_Assitance, String amount, String date, String sponsored_by,String notedBy,
                 String noteDate, String time) {
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
        NotedBy = notedBy;
        NoteDate = noteDate;
        Time = time;


    }

    public  String getNotedBy(){
        return NotedBy;
    }
    public void setNotedBy(String notedBy){
        NotedBy = notedBy;
    }

    public  String getNoteDate(){
        return NoteDate;
    }
    public void setNoteDate(String noteDate){
        Time = noteDate;
    }

    public  String getTime(){
        return Time;
    }
    public void setTime(String time){
        Time = time;
    }


    public  String getIDnumber(){return  IDnumber;}
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




