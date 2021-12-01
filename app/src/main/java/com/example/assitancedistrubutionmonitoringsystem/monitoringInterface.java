package com.example.assitancedistrubutionmonitoringsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class monitoringInterface extends AppCompatActivity {
    CardView Register,ListofRes,Logout,eDit,SmsAnnounce,OnlineAnnounce;
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_interface);
        builder = new AlertDialog.Builder(this);
        Register = findViewById(R.id.Register);
        ListofRes = findViewById(R.id.List);
        eDit = findViewById(R.id.Edit);
        SmsAnnounce = findViewById(R.id.SmsAnnouncement);
        OnlineAnnounce = findViewById(R.id.OnlineAnnounce);
        Logout = findViewById(R.id.Logout);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten  =  new Intent(monitoringInterface.this,RegisterInterface.class);
                startActivity(inten);
            }
        });

        ListofRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten  =  new Intent(monitoringInterface.this,ListofReciepientInterface.class);
                startActivity(inten);
            }
        });

        eDit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten  =  new Intent(monitoringInterface.this,EditAndDeleteInfo.class);
                startActivity(inten);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Logout");
                      builder.setMessage("Do you want to logout?");
                        builder.setCancelable(true);
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finish();
                                startActivity(new Intent(monitoringInterface.this,adminLogInterface.class));
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

            }
        });

        SmsAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten  =  new Intent(monitoringInterface.this,SMSAnnouncement.class);
                startActivity(inten);

            }
        });

        OnlineAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten  =  new Intent(monitoringInterface.this,OnlineAnnouncement.class);
                startActivity(inten);
            }
        });



    }


}