package com.example.assitancedistrubutionmonitoringsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class mylist extends FirebaseRecyclerAdapter<List,mylist.myviewholder> {


    public mylist(@NonNull FirebaseRecyclerOptions<List> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull List list) {
        holder.idnum.setText(list.getIDnumber());
        holder.fiName.setText(list.getFirstName());
        holder.miName.setText(list.getMiddleName());
        holder.laName.setText(list.getLastName());
        holder.ConNum.setText(list.getContact());
        holder.emailAdd.setText(list.getEmail());
        holder.Gender.setText(list.getGender());
        holder.Stat.setText(list.getStatus());
        holder.typeofAssist.setText(list.getTypeofAssitance());
        holder.Amount.setText(list.getAmount());
        holder.Date.setText(list.getDate());
        holder.Spon.setText(list.getSponsoredBy());
        holder.Notedby.setText(list.getNotedBy());
        holder.nOteDate.setText(list.getNoteDate());
        holder.tIme.setText(list.getTime());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listofreciept2, parent, false);
        return new myviewholder(view);

    }


    class myviewholder extends RecyclerView.ViewHolder {

        TextView idnum, fiName, miName, laName, ConNum, emailAdd, Gender, Stat, typeofAssist, Amount, Date, Spon,Notedby,nOteDate,tIme;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            idnum = (TextView) itemView.findViewById(R.id.idnumlist);
            fiName = (TextView) itemView.findViewById(R.id.firstName);
            miName = (TextView) itemView.findViewById(R.id.middleName);
            laName = (TextView) itemView.findViewById(R.id.lastname);
            ConNum = (TextView) itemView.findViewById(R.id.Contact);
            emailAdd = (TextView) itemView.findViewById(R.id.EmailAdd);
            Gender = (TextView) itemView.findViewById(R.id.Sex);
            Stat = (TextView) itemView.findViewById(R.id.Status);
            typeofAssist = (TextView) itemView.findViewById(R.id.Type);
            Amount = (TextView) itemView.findViewById(R.id.amount);
            Date = (TextView) itemView.findViewById(R.id.date);
            Spon = (TextView) itemView.findViewById(R.id.sponsor);
            Notedby = (TextView) itemView.findViewById(R.id.LISTnoted_by);
            nOteDate = (TextView) itemView.findViewById(R.id.LISTNotedDate);
            tIme = (TextView) itemView.findViewById(R.id.LISTTime);


        }
    }
}
