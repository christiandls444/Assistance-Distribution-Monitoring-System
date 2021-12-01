package com.example.assitancedistrubutionmonitoringsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {
    ImageView ViewProf,AnnounceUser;
    private ImageView logout;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        builder = new AlertDialog.Builder(this);
        logout = findViewById(R.id.User_Logout);
        ViewProf = findViewById(R.id.View);
        AnnounceUser = findViewById(R.id.USerAnnouncement);



        ViewProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentuser = new Intent(UserProfile.this,SearchDataUser.class);
                startActivity(intentuser);

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Logout")
                        .setMessage("Do you want to logout?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                 FirebaseAuth.getInstance().signOut();
                                finish();
                                startActivity(new Intent(UserProfile.this,UserLoginInterface.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();






            }
        });






        AnnounceUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentuser = new Intent(UserProfile.this,User_Announcement.class);
                startActivity(intentuser);


            }
        });
    }
}