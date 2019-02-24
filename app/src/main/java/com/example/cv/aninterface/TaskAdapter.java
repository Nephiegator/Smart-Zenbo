package com.example.cv.aninterface;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.remtaskViewHolder> {

    private Context mCtx;
    private List<dbReminder> remtasklist;
    private CheckBox checkBox;

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

        remtasklist = new ArrayList<>();
        dbReminder reminder = remtasklist.get(position);


        holder.textViewTitle.setText(reminder.getTitle());
        holder.textViewDecs.setText("   "+reminder.getDesc());
        holder.textViewLoc.setText("At room: " + reminder.getInLocation());
        holder.textViewPer.setText("To: " + reminder.getObjPerson());
        holder.checkBox.setChecked(reminder.isSelected());

        holder.setItemClickListener(new remtaskViewHolder.ItemClickListener(){
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox myCheckBox= (CheckBox) v;
                dbReminder currentReminder = remtasklist.get(pos);

                if(myCheckBox.isChecked()) {
                    currentReminder.setSelected(true);
                    //currentReminder.
                }
                else if(!myCheckBox.isChecked()) {
                    currentReminder.setSelected(false);
                    //currentReminder.remove(currentReminder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return remtasklist.size();
    }

    class remtaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewTitle, textViewDecs, textViewLoc, textViewPer;
        ItemClickListener itemClickListener;
        CheckBox checkBox;

        public remtaskViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textView_title_task);
            textViewDecs = itemView.findViewById(R.id.textView_desc_task);
            textViewLoc = itemView.findViewById(R.id.textView_loc_task);
            textViewPer = itemView.findViewById(R.id.textView_per_task);
            checkBox = itemView.findViewById(R.id.checkBox);

            itemView.setOnClickListener(this);
            checkBox.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }
        interface ItemClickListener {

            void onItemClick(View v,int pos);
        }



        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
            dbReminder tt = remtasklist.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, UpdateTask.class);
            intent.putExtra("Reminder", tt);
            mCtx.startActivity(intent);
        }
    }


}
