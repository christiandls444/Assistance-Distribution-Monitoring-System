package com.example.assitancedistrubutionmonitoringsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class admin_and_user extends AppCompatActivity {
    ImageButton Admin,User;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_and_user);
        builder = new AlertDialog.Builder(this);
        Admin = findViewById(R.id.admin);
        User =  findViewById(R.id.user);
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Warning")
                        .setMessage("Unauthorized user may result in administrative disciplinary action, civil charges or criminal penalties and other sanction. Cancel if not an administrator")
                        .setCancelable(true)
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent inten  =  new Intent(admin_and_user.this,adminLogInterface.class);
                                startActivity(inten);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

               
            }
        });
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentuser = new Intent(admin_and_user.this,UserLoginInterface.class);
                startActivity(intentuser);
            }
        });
    }
}