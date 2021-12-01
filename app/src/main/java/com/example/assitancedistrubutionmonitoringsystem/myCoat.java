package com.example.assitancedistrubutionmonitoringsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myCoat extends RecyclerView.Adapter<myCoat.MyviewHolder> {

    private User_Announcement user_announcement;
    private List<coat> mCoat;
    public myCoat(User_Announcement user_announcement, List<coat> mCoat){

        this.user_announcement = user_announcement;
        this.mCoat = mCoat;

    }


    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(user_announcement).inflate(R.layout.userannouncelist,parent,false);
        return new MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        holder.tittle.setText(mCoat.get(position).getTittle());
        holder.message.setText(mCoat.get(position).getMessage());
        holder.datepost.setText((mCoat.get(position).getDatePost()));
        holder.timepost.setText(mCoat.get(position).getTimePost());

    }

    @Override
    public int getItemCount() {
       return mCoat.size();
    }

    public  static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView tittle, message, datepost, timepost;
    public MyviewHolder(@NonNull View itemView) {

        super(itemView);

        tittle = itemView.findViewById(R.id.User_Tittle);
        message = itemView.findViewById(R.id.User_Message);
        datepost = itemView.findViewById(R.id.User_DatedPosted);
        timepost = itemView.findViewById(R.id.User_TimePosted);



    }
}


}
