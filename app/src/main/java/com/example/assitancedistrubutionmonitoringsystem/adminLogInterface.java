package com.example.assitancedistrubutionmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class adminLogInterface extends AppCompatActivity {
TextView Login;
EditText AdminEmail,AdminPass;
CheckBox showpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_interface);
      Login   =  findViewById(R.id.LogIn);
      AdminEmail  = findViewById(R.id.admin_email);
      AdminPass = findViewById(R.id.admin_password);
      showpassword = findViewById(R.id.adminlogin_showpass);


      showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (isChecked){
                  AdminPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

              }else{
                  AdminPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
              }
          }
      });


      Login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              if (AdminEmail.getText().toString().trim().equals("Admin")&&AdminPass.getText().toString().trim().equals("Admin")){

                  AdminPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                  Toast.makeText(adminLogInterface.this, "Successfully Login!", Toast.LENGTH_SHORT).show();
                  Intent inten = new Intent(adminLogInterface.this, monitoringInterface.class);
                  startActivity(inten);
                  finish();

              }
              else {
                  Toast.makeText(adminLogInterface.this, "Invalid Username and Password, Try Again!", Toast.LENGTH_SHORT).show();
              }

              }
      });


    }
}