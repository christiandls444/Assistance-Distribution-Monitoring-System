package com.example.assitancedistrubutionmonitoringsystem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;

public class myadapter extends FirebaseRecyclerAdapter<adapter,myadapter.myviewholder>

{
    public myadapter(@NonNull FirebaseRecyclerOptions<adapter> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final adapter model) {

        holder.idnumedit.setText(model.getIDnumber());
        holder.fIName.setText(model.getFirstName());
        holder.mIName.setText(model.getMiddleName());
        holder.lAName.setText(model.getLastName());
        holder.CoNNum.setText(model.getContact());
        holder.eMailAdd.setText(model.getEmail());
        holder.GeNder.setText(model.getGender());
        holder.StAt.setText(model.getStatus());
        holder.tYpeofAssist.setText(model.getTypeofAssitance());
        holder.AmOunt.setText(model.getAmount());
        holder.DaTe.setText(model.getDate());
        holder.SpOn.setText(model.getSponsoredBy());
        holder.notedBy.setText(model.getNotedBy());
        holder.noteDATE.setText(model.getNoteDate());
        holder.TIME.setText(model.getTime());

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.idnumedit.getContext()).setContentHolder(new ViewHolder(R.layout.updatedialog)).setExpanded(true,1100 ).create();


            View myview = dialogPlus.getHolderView();
            final EditText upIDnum = myview.findViewById(R.id.upIDnum);
            final EditText upFName = myview.findViewById(R.id.upFname);
            final EditText upMName = myview.findViewById(R.id.upMname);
            final EditText upLName = myview.findViewById(R.id.upLname);
            final   EditText upCOtact = myview.findViewById(R.id.upContact);
            final EditText upEmaiLAdd = myview.findViewById(R.id.upEmail);
            final EditText upSeX = myview.findViewById(R.id.upSex);
            final EditText upStatUs = myview.findViewById(R.id.upStatus);
            final EditText upTypeOfAss = myview.findViewById(R.id.upTOA);
            final EditText upAmounT = myview.findViewById(R.id.upAmount);
            final EditText upDate = myview.findViewById(R.id.upDate);
            final EditText upSPonsor = myview.findViewById(R.id.upSponsor);
            final  EditText upNotedby = myview.findViewById(R.id.upnotedby);
            final  EditText upTIME = myview.findViewById(R.id.upTIme);
            final EditText upNoteDate = myview.findViewById(R.id.upNoteDate);
                  TextView Updatebtn = myview.findViewById(R.id.updateBtn);

                upIDnum.setText(model.getIDnumber());
                upFName.setText(model.getFirstName());
                upMName.setText(model.getMiddleName());
                upLName.setText(model.getLastName());
                upCOtact.setText(model.getContact());
                upEmaiLAdd.setText(model.getEmail());
                upSeX.setText(model.getGender());
                upStatUs.setText(model.getStatus());
                upTypeOfAss.setText(model.getTypeofAssitance());
                upAmounT.setText(model.getAmount());
                upDate.setText(model.getDate());
                upSPonsor.setText(model.getSponsoredBy());
                upNotedby.setText(model.getNotedBy());
                upNoteDate.setText(model.getNoteDate());
                upTIME.setText(model.getTime());
                dialogPlus.show();

                //Update
                Updatebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("IDnumber",upIDnum.getText().toString());
                        userMap.put("FirstName",upFName.getText().toString());
                        userMap.put("MiddleName",upMName.getText().toString());
                        userMap.put("LastName",upLName.getText().toString());
                        userMap.put("Contact",upCOtact.getText().toString());
                        userMap.put("Email",upEmaiLAdd.getText().toString());
                        userMap.put("TypeofAssitance",upTypeOfAss.getText().toString());
                        userMap.put("Amount",upAmounT.getText().toString());
                        userMap.put("Date",upDate.getText().toString());
                        userMap.put("SponsoredBy",upSPonsor.getText().toString());
                        userMap.put("Gender",upSeX.getText().toString());
                        userMap.put("Status",upStatUs.getText().toString());
                        userMap.put("NotedBy",upNotedby.getText().toString());
                        userMap.put("NoteDate",upNoteDate.getText().toString());
                        userMap.put("Time",upTIME.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Admin").child(getRef(position).getKey()).updateChildren(userMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();

                                    }
                                })     .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });


                    }
                });







            }
        });
        //Delete

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build =  new AlertDialog.Builder(holder.fIName.getContext());
                build.setTitle("Delete");
                build.setMessage("Do you want to Delete this Information?");

                build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int q) {
                        FirebaseDatabase.getInstance().getReference().child("Admin").child(getRef(position).getKey()).removeValue();

                    }
                });
                build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int q) {
                        dialog.dismiss();

                    }
                });

                build.show();

            }
        });
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.editanddeleteinfo,parent,false);
        return new myviewholder(view);

    }



    class myviewholder extends RecyclerView.ViewHolder{
        ImageView update,delete;
        TextView idnumedit,fIName,mIName,lAName,CoNNum,eMailAdd,GeNder,StAt,tYpeofAssist,AmOunt,DaTe,SpOn,notedBy,noteDATE,TIME;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            idnumedit = (TextView)itemView.findViewById(R.id.idnumedit);
            fIName = (TextView)itemView.findViewById(R.id.fIrstName);
            mIName = (TextView)itemView.findViewById(R.id.mIddleName);
            lAName = (TextView)itemView.findViewById(R.id.lAstname);
            CoNNum = (TextView)itemView.findViewById(R.id.CONtact);
            eMailAdd = (TextView)itemView.findViewById(R.id.EMAilAdd);
            GeNder = (TextView)itemView.findViewById(R.id.SeX);
            StAt = (TextView)itemView.findViewById(R.id.StatuS);
            tYpeofAssist = (TextView)itemView.findViewById(R.id.TypE);
            AmOunt = (TextView)itemView.findViewById(R.id.Amount);
            DaTe= (TextView)itemView.findViewById(R.id.Date);
            SpOn = (TextView)itemView.findViewById(R.id.Sponsor);
            notedBy = (TextView)itemView.findViewById(R.id.UDnoted_by);
            noteDATE = (TextView)itemView.findViewById(R.id.UDnotedDate);
            TIME = (TextView)itemView.findViewById(R.id.UDTime);
            update = (ImageView)itemView.findViewById(R.id.Update);
            delete = (ImageView)itemView.findViewById(R.id.Delete);





        }
    }

}
