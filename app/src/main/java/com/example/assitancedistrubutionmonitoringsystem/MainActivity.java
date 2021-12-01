package com.example.assitancedistrubutionmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView carehand;
    TextView WeCare;
    Animation ounce, talon;
    private  static  int Splash =  2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carehand = findViewById(R.id.handshake);
        WeCare = findViewById(R.id.wecare);
        ounce  = AnimationUtils.loadAnimation(this,R.anim.animation);
        talon =  AnimationUtils.loadAnimation(this,R.anim.animation);
        carehand.setAnimation(ounce);
        WeCare.setAnimation(talon);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inten  =  new Intent(MainActivity.this,admin_and_user.class);
                startActivity(inten);
                finish();
            }
        },Splash);
    }
}