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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class UserLoginInterface extends AppCompatActivity {
    private TextView signUP,LogInBtn,forgetPass;
    private EditText UserEmail,UserPassword;
    private FirebaseAuth mAuth;
    private FirebaseDatabase root = FirebaseDatabase.getInstance();
    CheckBox showpassworduser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_interface);
        //Button
        signUP = findViewById(R.id.signup);
        LogInBtn =  findViewById(R.id.User_LogInBtn);
        //EditText
        UserEmail = findViewById(R.id.user_email_login);
        UserPassword = findViewById(R.id.user_password_login);
        mAuth = FirebaseAuth.getInstance();
        forgetPass =findViewById(R.id.ForgetPass);
        //showpass
        showpassworduser = findViewById(R.id.userlogin_showpass);

        showpassworduser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    UserPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    UserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        //Signup Button
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent inten  =  new Intent(UserLoginInterface.this,SignupInterface.class);
                startActivity(inten);
                UserEmail.setText("");
                UserPassword.setText("");

            }
        });

        //Forget Password Button
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten  =  new Intent(UserLoginInterface.this,ForgetPasswordInterface.class);
                startActivity(inten);
                UserEmail.setText("");
                UserPassword.setText("");

            }
        });

        //LoginButton
        LogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String varUserEmail = UserEmail.getText().toString();
                String varUserPass = UserPassword.getText().toString();

                if (varUserEmail.isEmpty()){
                    UserEmail.setError("Email is required!");
                    UserEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(varUserEmail).matches()) {
                    UserEmail.setError("Please Enter a valid email!");
                    UserEmail.requestFocus();
                    return;
                }

                if (varUserPass.isEmpty()){
                    UserPassword.setError("Password is required!");
                    UserPassword.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(varUserEmail,varUserPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if ((user.isEmailVerified())){


                            String email = UserEmail.getText().toString();
                            String pass = UserPassword.getText().toString();

                            LoginMonitor loginMonitor = new LoginMonitor(email,pass);
                            FirebaseDatabase.getInstance().getReference("Login").child(email.replace(".",",")).setValue(loginMonitor);

                                     finish();
                                    startActivity(new Intent(UserLoginInterface.this,UserProfile.class));
                                    UserEmail.setText("");
                                    UserPassword.setText("");


                        } else{
                            user.sendEmailVerification();
                            Toast.makeText(UserLoginInterface.this,"Check your email to verify your account!",Toast.LENGTH_LONG).show();

                        }
                    }
                    else {
                        Toast.makeText(UserLoginInterface.this,"Failed to Login! Please check your credentials.",Toast.LENGTH_LONG).show();
                    }

                    }
                });



            }
        });




    }
}