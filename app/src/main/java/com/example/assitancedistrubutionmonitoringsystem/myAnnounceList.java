package com.example.assitancedistrubutionmonitoringsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class myAnnounceList extends RecyclerView.Adapter<myAnnounceList.MyviewHolder>
{
    private ListofAnnouncement listofAnnouncement;
    private List<model> mlist;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public myAnnounceList(ListofAnnouncement listofAnnouncement, List<model> mlist){

      this.listofAnnouncement = listofAnnouncement;
      this.mlist = mlist;

    }

    public void UpdateData(int position){
        model item  = mlist.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId",item.getId());
        bundle.putString("uTittle",item.getTittle());
        bundle.putString("uMessage",item.getMessage());
        bundle.putString("uDatePost",item.getDatePost());
        bundle.putString("uTimePost",item.getTimePost());

        Intent intent = new Intent(listofAnnouncement,OnlineAnnouncement.class);
        intent.putExtras(bundle);
        listofAnnouncement.startActivity(intent);
        listofAnnouncement.finish();



    }

    public void DeleteData(int position) {
        model item  = mlist.get(position);
      db.collection("Announcement").document(item.getId()).delete()
              .addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if (task.isSuccessful()){
                            notifyRemoved(position);
                          Toast.makeText(listofAnnouncement,"Deleted",Toast.LENGTH_SHORT).show();

                      }else {
                          Toast.makeText(listofAnnouncement,"Deleted"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  }
              });


}
private void  notifyRemoved(int position){

    mlist.remove(position);
    notifyItemRemoved(position);
   listofAnnouncement.showList();

}

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(listofAnnouncement).inflate(R.layout.announcementlist_admin,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        //Save values
       holder.tittle.setText(mlist.get(position).getTittle());
        holder.Message.setText(mlist.get(position).getMessage());
        holder.DatePosted.setText(mlist.get(position).getDatePost());
        holder.TimePosted.setText(mlist.get(position).getTimePost());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView tittle, Message,DatePosted,TimePosted;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            tittle =  itemView.findViewById(R.id.Admin_Tittle);
            Message = itemView.findViewById(R.id.Admin_Message);
            DatePosted = itemView.findViewById(R.id.Admin_DatedPosted);
            TimePosted = itemView.findViewById(R.id.Admin_TimePosted);


        }
    }


}
