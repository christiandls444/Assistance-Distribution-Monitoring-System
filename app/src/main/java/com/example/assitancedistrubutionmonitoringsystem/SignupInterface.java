package com.example.assitancedistrubutionmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignupInterface extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText Username,SignupEmail,SignupPassword;
    private TextView SignupBtn;
    AlertDialog.Builder builder;
    CheckBox showpassworduserSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_interface);
        mAuth = FirebaseAuth.getInstance();
        builder = new AlertDialog.Builder(this);
        Username = findViewById(R.id.user_username);
        SignupEmail = findViewById(R.id.user_email);
        SignupPassword = findViewById(R.id.user_password);
        SignupBtn = findViewById(R.id.signUp);
        showpassworduserSignup = findViewById(R.id.usersignup_showpass);



        showpassworduserSignup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SignupPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    SignupPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("END USER LICENSE AGREEMENT")
                        .setMessage("This Agreement is Entered into a Project of Mobile apps for Assistance Distribution Monitoring System for Barangay San Jose Mandaluyong City\n" +
                                "Whereas, License to software for the purpose of all works developed by the Researcher’s\\n\\n\n" +
                                "1.\tLicense Mobile apps for Assistance Distribution Monitoring System\n" +
                                "(Nontransferable limited to download, install and use the application for your personal purposes strictly accordance with the terms of this Agreement.\n" +
                                "You may not:\\n\\n\n" +
                                "•\tModify, Translate or create derivative works base on the software.\\n\n" +
                                "•\tBuild a system using a similar ideas, features and functions of the software.\\n\n" +
                                "•\tUse this software for anomalous.\\n\\n\n" +
                                "\n" +
                                "By clicking the “I agree” Button downloading or using the Application you are agreeing to be bound by the terms and condition of this Agreement. If you do not agree to the terms, do not click “I Agree” Button and do not download and use this Application\n")
                        .setCancelable(true)
                        .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String varUsername = Username.getText().toString();
                                String varEmail = SignupEmail.getText().toString();
                                String varPassword = SignupPassword.getText().toString();

                                if (varUsername.isEmpty()){
                                    Username.setError("Username is required!");
                                    Username.requestFocus();
                                    return;
                                }
                                if (varEmail.isEmpty())
                                {
                                    SignupEmail.setError("Email is required!");
                                    SignupEmail.requestFocus();
                                    return;
                                }
                                if (varPassword.isEmpty()){
                                    SignupPassword.setError("Passwrod is required!");
                                    SignupPassword.requestFocus();
                                    return;
                                }
                                if (!Patterns.EMAIL_ADDRESS.matcher(varEmail).matches()) {
                                    SignupEmail.setError("Please provide valid email");
                                    SignupEmail.requestFocus();
                                    return;
                                }
                                mAuth.createUserWithEmailAndPassword(varEmail,varPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            User user = new User(varUsername,varEmail);
                                            FirebaseDatabase.getInstance().getReference("Sign Up").child(varUsername.replace(".",",")).child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){

                                                        Toast.makeText(SignupInterface.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                                                        Username.setText("");
                                                        SignupEmail.setText("");
                                                        SignupPassword.setText("");
                                                        finish();
                                                        startActivity(new Intent(SignupInterface.this,UserLoginInterface.class));
                                                    }
                                                    else{
                                                        Toast.makeText(SignupInterface.this,"Email is already used!",Toast.LENGTH_LONG).show();

                                                    }
                                                }
                                            });

                                        }
                                        else{
                                            Toast.makeText(SignupInterface.this,"Email is already used, Try Again!",Toast.LENGTH_LONG).show();

                                        }
                                    }

                                });

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
    }
}