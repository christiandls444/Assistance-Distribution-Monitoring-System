package com.example.assitancedistrubutionmonitoringsystem;

import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class TouchHelper extends ItemTouchHelper.SimpleCallback {


        private myAnnounceList myAnnounceList1;


    public TouchHelper(myAnnounceList myAnnounceList1) {
        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);

       this.myAnnounceList1 = myAnnounceList1;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        final  int position = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT){

            myAnnounceList1.UpdateData(position);
            myAnnounceList1.notifyDataSetChanged();

        }


        else {

            myAnnounceList1.DeleteData(position);

        }



    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeRightBackgroundColor(Color.RED)
                .addSwipeRightActionIcon(R.drawable.deleteannounce)
                .addSwipeLeftBackgroundColor(Color.DKGRAY)
                .addSwipeLeftActionIcon(R.drawable.editannounce)
                .create()
                .decorate();

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
