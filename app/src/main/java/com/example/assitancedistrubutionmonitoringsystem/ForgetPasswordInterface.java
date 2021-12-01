package com.example.assitancedistrubutionmonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordInterface extends AppCompatActivity {

    private EditText ResetPassEmail;
    private TextView ResetPassBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_interface);

        ResetPassEmail = findViewById(R.id.forgetpassEmail);
        ResetPassBtn = findViewById(R.id.ResetPass);

        mAuth = FirebaseAuth.getInstance();

        ResetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ResetEmail = ResetPassEmail.getText().toString();
                if (ResetEmail.isEmpty()) {
                    ResetPassEmail.setError("Email is required!");
                    ResetPassEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(ResetEmail).matches()) {
                    ResetPassEmail.setError("Please provide valid email");
                    ResetPassEmail.requestFocus();
                    return;
                }
                mAuth.sendPasswordResetEmail(ResetEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgetPasswordInterface.this,"Check your email to reset your password!",Toast.LENGTH_LONG).show();
                            ResetPassEmail.setText("");
                        }else {
                            Toast.makeText(ForgetPasswordInterface.this,"Please Try again! Something wrong happened!",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });
    }
}