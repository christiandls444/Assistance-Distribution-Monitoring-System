package com.example.assitancedistrubutionmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class OnlineAnnouncement extends AppCompatActivity {
   private EditText TittleAnn,MessAnn;
   private TextView DatePost,TimePost;
   private ImageView ListAnn,SendAnn;
   private FirebaseFirestore db = FirebaseFirestore.getInstance();
   private String uId,uTittle,uMessage,uDatePost,uTimePost;
    DatePickerDialog.OnDateSetListener setListener;
    int t1hour, t1Minute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_announcement);
        TittleAnn = findViewById(R.id.OLPhoneNo);
        MessAnn = findViewById(R.id.OLMessageAnnounce);
        ListAnn = findViewById(R.id.ListAnnounce);
        SendAnn = findViewById(R.id.sendAnnounce);
        DatePost = findViewById(R.id.OLDatePostedAnnounceAnnounce);
        TimePost = findViewById(R.id.OLTimePostedAnnounceAnnounce);

        //Date
        Calendar calendar  = Calendar.getInstance();
        final  int year =   calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day  = calendar.get(Calendar.DAY_OF_MONTH);


        DatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(OnlineAnnouncement.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String Date = day +"/"+month+"/"+year;
                DatePost.setText(Date);
            }
        };


        TimePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(OnlineAnnouncement.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1hour = hourOfDay;
                        t1Minute = minute;
                        String time = t1hour + ":" + t1Minute;

                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "HH:mm"

                        );
                        try {
                            Date date  =  dateFormat.parse(time);

                            SimpleDateFormat f12H = new SimpleDateFormat("hh:mm aa");

                            TimePost.setText(f12H.format(date));

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                },12,0,false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1hour,t1Minute);
                timePickerDialog.show();

            }
        });







        Bundle bundle =getIntent().getExtras();
        if (bundle != null){

            uTittle = bundle.getString("uTittle");
            uId  = bundle.getString("uId");
            uMessage = bundle.getString("uMessage");
           uDatePost = bundle.getString("uDatePost");
           uTimePost = bundle.getString("uTimePost");

            TittleAnn.setText(uTittle);
            MessAnn.setText(uMessage);
            DatePost.setText(uDatePost);
            TimePost.setText(uTimePost);



        }else{



        }








        SendAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String varTittle = TittleAnn.getText().toString();
                String varMess = MessAnn.getText().toString();
                String varDatePosted = DatePost.getText().toString();
                String varTimePosted = TimePost.getText().toString();


                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null){

                    String id = uId;
                    updateToFirestore(id,varTittle,varMess,varDatePosted,varTimePosted);

                }else{
                    String id = UUID.randomUUID().toString();

                    saveTofirestore(id,varTittle,varMess,varDatePosted,varTimePosted);
                }


            }
        });

        ListAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentuser = new Intent(OnlineAnnouncement.this,ListofAnnouncement.class);
                startActivity(intentuser);
            }
        });


    }

    private void updateToFirestore(String id, String varTittle, String varMess,String varDatePost,String varTimePost) {

        db.collection("Announcement").document(id).update("Tittle",varTittle,"Message",varMess,"DatePost",varDatePost,"TimePost",varTimePost)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        if (task.isSuccessful()){

                            Toast.makeText(OnlineAnnouncement.this,"Announcement has been updated!",Toast.LENGTH_LONG).show();
                            TittleAnn.setText("");
                            MessAnn.setText("");
                            DatePost.setText("");
                            TimePost.setText("");
                           finish();



                        }else{

                            Toast.makeText(OnlineAnnouncement.this,"Error : "+task.getException().getMessage(),Toast.LENGTH_LONG).show();


                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(OnlineAnnouncement.this,e.getMessage(),Toast.LENGTH_LONG).show();

            }
        });



    }

    private void saveTofirestore(String id, String varTittle, String varMess,String vardatePosted,String vartimePosted) {

        if (!varTittle.isEmpty() && !varMess.isEmpty()){

            HashMap<String,Object> map = new HashMap<>();
            map.put("id",id);
            map.put("Tittle",varTittle);
            map.put("Message",varMess);
            map.put("DatePost",vardatePosted);
            map.put("TimePost",vartimePosted);

            db.collection("Announcement").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(OnlineAnnouncement.this,"Announced!",Toast.LENGTH_LONG).show();
                        TittleAnn.setText("");
                        MessAnn.setText("");
                        DatePost.setText("");
                        TimePost.setText("");
                    }
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(OnlineAnnouncement.this,"Failed to Announce",Toast.LENGTH_LONG).show();
                        }
                    });


        }
        else{

            Toast.makeText(OnlineAnnouncement.this,"Empty Field is not allowed",Toast.LENGTH_LONG).show();
        }
    }
}