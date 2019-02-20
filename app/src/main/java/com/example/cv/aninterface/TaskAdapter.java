package com.example.cv.aninterface;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TaskAdapter extends FirestoreRecyclerAdapter<dbReminder, TaskAdapter.TaskHolder>{

    public TaskAdapter(@NonNull FirestoreRecyclerOptions<dbReminder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskHolder holder, int position, @NonNull dbReminder model) {
        holder.txt_tile.setText(model.getTitle());
        holder.txt_desc.setText(model.getDesc());
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itemtask, parent, false);
        return null;
    }

    class TaskHolder extends RecyclerView.ViewHolder{
        TextView txt_tile;
        TextView txt_desc;
        Spinner txt_room;
        Spinner txt_pers;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            txt_tile = itemView.findViewById(R.id.textView_per_task);
            txt_desc = itemView.findViewById(R.id.textView_per_task);

        }
    }

}
