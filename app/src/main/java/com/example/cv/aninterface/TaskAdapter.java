package com.example.cv.aninterface;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

import bolts.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.remtaskViewHolder> {

    private Context mCtx;
    private List<dbReminder> remtasklist;

    public TaskAdapter (Context mCtx, List<dbReminder> remtasklist) {
        this.mCtx = mCtx;
        this.remtasklist = remtasklist;
    }


    @NonNull
    @Override
    public remtaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new remtaskViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.list_itemtask,parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull remtaskViewHolder holder, int position) {
        dbReminder reminder = remtasklist.get(position);

        holder.textViewTitle.setText(reminder.getTitle());
        holder.textViewDecs.setText(reminder.getDesc());
        holder.textViewLoc.setText(reminder.getInLocation());
        holder.textViewPer.setText(reminder.getObjPerson());
    }

    @Override
    public int getItemCount() {
        return remtasklist.size();
    }

    class remtaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDecs, textViewLoc, textViewPer;

        public remtaskViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textView_title_task);
            textViewDecs = itemView.findViewById(R.id.textView_desc_task);
            textViewLoc = itemView.findViewById(R.id.textView_loc_task);
            textViewPer = itemView.findViewById(R.id.textView_per_task);
        }
    }


}
