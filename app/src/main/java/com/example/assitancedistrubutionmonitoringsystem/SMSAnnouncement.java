package com.example.assitancedistrubutionmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SMSAnnouncement extends AppCompatActivity {

   private TextView SendBtn;
   private EditText PhoneNO,AnnounceMESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s_announcement);

        SendBtn = findViewById(R.id.Send);
        PhoneNO = findViewById(R.id.PhoneNo);
        AnnounceMESS = findViewById(R.id.MessageAnnounce);



        SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        smsSend();
                    }
                    else{
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS},1);
                    }

                }




            }
        });

    }

    public void smsSend()
    {

        String varPhoneno = PhoneNO.getText().toString();
        String varAnnounceSMS = AnnounceMESS.getText().toString();
try {
    SmsManager smsManager = SmsManager.getDefault();
    smsManager.sendTextMessage(varPhoneno,null,varAnnounceSMS,null,null);
    Toast.makeText(this,"Message is sent",Toast.LENGTH_SHORT).show();
    AnnounceMESS.setText("");
    PhoneNO.setText("");

} catch (Exception e){

    e.printStackTrace();
    Toast.makeText(this,"Failed to send message",Toast.LENGTH_SHORT).show();
}


    }



}