package com.example.assitancedistrubutionmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class    RegisterInterface extends AppCompatActivity {
private EditText FirstName,MidName,LastName, Contact,Email,TypeAssist,amount,PersonSpon,IdNUm,BrgyNote;
private TextView DateNoted,Time,date;
DatePickerDialog.OnDateSetListener setListener,setListener1;
RadioButton Male,Female,recieve,notyet;
TextView Register;
int t1hour, t1Minute;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("Admin");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_interface);

        IdNUm = findViewById(R.id.ID_number);
        FirstName = findViewById(R.id.FirstNameRegis);
        MidName  = findViewById(R.id.MiddleNameRegis);
        LastName =  findViewById(R.id.LastNameRegis);
        Contact = findViewById(R.id.contact_Number);
        Email = findViewById(R.id.EmailAddress);
        TypeAssist = findViewById(R.id.Type_of_Assitance);
        amount = findViewById(R.id.Amount);
        date = findViewById(R.id.Date);
        PersonSpon =  findViewById(R.id.Sponsor);
        recieve = findViewById(R.id.recieveStatus);
        notyet = findViewById(R.id.Not_Yet_recivedStatus);
        Male = findViewById(R.id.male);
        Female = findViewById(R.id.female);
        BrgyNote = findViewById(R.id.Brgyofficials);
        DateNoted = findViewById(R.id.Date_noted);
        Time = findViewById(R.id.Time_noted);
        Register = findViewById(R.id.Register_btn);

        //Note Date
        Calendar calendar  = Calendar.getInstance();
        final  int year =   calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day  = calendar.get(Calendar.DAY_OF_MONTH);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterInterface.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener1,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        setListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String Date = day +"/"+month+"/"+year;
                date.setText(Date);
            }
        };


        DateNoted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterInterface.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
                DateNoted.setText(date);
            }
        };



        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(RegisterInterface.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
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

                            Time.setText(f12H.format(date));

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



Register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {




        String varFName = FirstName.getText().toString();
        String varMName = MidName.getText().toString();
        String varLName = LastName.getText().toString();
        String varContact = Contact.getText().toString();
        String varEmail = Email.getText().toString();
        String varTypeofAssist = TypeAssist.getText().toString();
        String varAmount = amount.getText().toString();
        String varDate = date.getText().toString();
        String varPersonSpon = PersonSpon.getText().toString();
        String varRecieveStat = recieve.getText().toString();
        String varNotRecieveStat = notyet.getText().toString();
        String varMale = Male.getText().toString();
        String varFemale = Female.getText().toString();
        String varIDnum = IdNUm.getText().toString();
        String varBrgyNote = BrgyNote.getText().toString();
        String varNoteDate = DateNoted.getText().toString();
        String varTime = Time.getText().toString();


        if(varBrgyNote.isEmpty()){
            BrgyNote.setError("This is required!");
            BrgyNote.requestFocus();
            return;
        }

        if(varIDnum.isEmpty()){
            IdNUm.setError("ID Number is required");
            IdNUm.requestFocus();
            return;
        }
        if(varFName.isEmpty()){
            FirstName.setError("FirstName is required");
            FirstName.requestFocus();
            return;
        }
        if(varMName.isEmpty()){
            MidName.setError("MiddleName is required");
            MidName.requestFocus();
            return;
        }
        if(varLName.isEmpty()){
            LastName.setError("LastName is required");
            LastName.requestFocus();
            return;
        }
        if(varContact.isEmpty()){
            Contact.setError("Contact is required");
            Contact.requestFocus();
            return;
        }
        if(varEmail.isEmpty()){
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }
        if(varTypeofAssist.isEmpty()){
            TypeAssist.setError("Type of Assistance is required");
            TypeAssist.requestFocus();
            return;
        }
        if(varAmount.isEmpty()){
            amount.setError("FirstName is required");
            amount.requestFocus();
            return;
        }

        if(varPersonSpon.isEmpty()){
            PersonSpon.setError("FirstName is required");
            PersonSpon.requestFocus();
            return;
        }


        Toast.makeText(RegisterInterface.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("IDnumber",varIDnum);
        userMap.put("FirstName",varFName);
        userMap.put("MiddleName",varMName);
        userMap.put("LastName",varLName);
        userMap.put("Contact",varContact);
        userMap.put("Email",varEmail);
        userMap.put("TypeofAssitance",varTypeofAssist);
        userMap.put("Amount",varAmount);
        userMap.put("Date",varDate);
        userMap.put("SponsoredBy",varPersonSpon);
        userMap.put("NotedBy",varBrgyNote);
        userMap.put("NoteDate",varNoteDate);
        userMap.put("Time",varTime);





        if (Male.isChecked()){
            userMap.put("Gender",varMale);
        }
        else
        {
            userMap.put("Gender",varFemale);
        }
        if (recieve.isChecked()){
            userMap.put("Status",varRecieveStat);
        }
        else {

            userMap.put("Status",varNotRecieveStat);


        }
        root.child(varIDnum).setValue(userMap);

        IdNUm.setText("");
        FirstName.setText("");
        MidName.setText("");
        LastName.setText("");
        Contact.setText("");
        Email.setText("");
        Male.setChecked(false);
        Female.setChecked(false);
        recieve.setChecked(false);
        notyet.setChecked(false);
        TypeAssist.setText("");
        amount.setText("");
        date.setText("");
        PersonSpon.setText("");
        BrgyNote.setText("");
        DateNoted.setText("");
        Time.setText("");





    }
});





    }
}