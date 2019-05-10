package com.example.cv.aninterface;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.comingtaskViewHolder> {

    private Context mCtx;
    private List<dbReminder> comingtasklist;


    public UpcomingAdapter (Context mCtx, List<dbReminder> comingtasklist) {
        this.mCtx = mCtx;
        this.comingtasklist = comingtasklist;
    }


    @NonNull
    @Override
    public comingtaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new comingtaskViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.home_list_item,parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull comingtaskViewHolder holder, int position) {
        dbReminder reminder = comingtasklist.get(position);

        holder.textViewTitle.setText(reminder.getTitle());
        holder.textViewLoc.setText(reminder.getInLocation());
        holder.textViewTime.setText(reminder.getTime());

    }

    @Override
    public int getItemCount() {
        return comingtasklist.size();
    }

    class comingtaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewLoc, textViewTime;


        public comingtaskViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textView_title_task);
            textViewLoc = itemView.findViewById(R.id.textView_loc_task);
            textViewTime = itemView.findViewById(R.id.textView_time_task);

            //itemView.setOnClickListener(this);

        }


//        @Override
//        public void onClick(View v) {
//            dbReminder tt = comingtasklist.get(getAdapterPosition());
//            Intent intent = new Intent(mCtx, UpdateTask.class);
//            intent.putExtra("Reminder", tt);
//            mCtx.startActivity(intent);
//        }
    }


}
