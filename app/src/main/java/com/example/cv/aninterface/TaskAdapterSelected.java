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

import java.util.List;

public class TaskAdapterSelected extends RecyclerView.Adapter<TaskAdapterSelected.remtaskselViewHolder> {

        private Context mCtx;
        private List<dbReminder> remtasklist;

        public TaskAdapterSelected (Context mCtx, List<dbReminder> remtasklist) {
            this.mCtx = mCtx;
            this.remtasklist = remtasklist;
        }

    @NonNull
    @Override
    public remtaskselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new remtaskselViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.list_itemtask_select,parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull remtaskselViewHolder holder, final int position) {

        //remtasklist = new ArrayList<>();
        dbReminder reminder = remtasklist.get(position);

        holder.textViewTitle.setText(reminder.getTitle());
        holder.textViewDecs.setText("   "+reminder.getDesc());
        holder.textViewLoc.setText("At room: " + reminder.getInLocation());
        holder.textViewPer.setText("To: " + reminder.getObjPerson());
        holder.myCheckBox.setChecked(reminder.isSelected());
        holder.myCheckBox.setTag(reminder);
/*
        holder.setItemClickListener(new remtaskselViewHolder.ItemClickListener() {
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

        holder.myCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < remtasklist.size(); i++) {
                    remtasklist.get(i).setSelected(false);
                }
                //Only select card that have clicked
                remtasklist.get(position).setSelected(true);
                notifyDataSetChanged();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return remtasklist.size();
    }


    class remtaskselViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView textViewTitle, textViewDecs, textViewLoc, textViewPer;
            CheckBox myCheckBox;
            //Context mCtx;  //copy from above
            //List<dbReminder> remtasklist;  //copy from above

            ItemClickListener itemClickListener;

            public remtaskselViewHolder(View itemView) {
                super(itemView);

                textViewTitle = itemView.findViewById(R.id.textView_title_task);
                textViewDecs = itemView.findViewById(R.id.textView_desc_task);
                textViewLoc = itemView.findViewById(R.id.textView_loc_task);
                textViewPer = itemView.findViewById(R.id.textView_per_task);
                myCheckBox = itemView.findViewById(R.id.checkBox);

               itemView.setOnClickListener(this);
               //myCheckBox.setOnClickListener(this);
            }
            public void setItemClickListener(ItemClickListener ic) {
                this.itemClickListener = ic;
            }

            @Override
            public void onClick(View v) {
                this.itemClickListener.onItemClick(v,getLayoutPosition());
                dbReminder tt = remtasklist.get(getAdapterPosition());

                Intent intent = new Intent(mCtx, UpdateTask.class);
                intent.putExtra("Reminder", tt);
                mCtx.startActivity(intent);
            }
            /*interface ItemClickListener {
                void onItemClick (View v, int pos);
            }
            public void onItemClickListener (View v, int pos) {

            }*/
        }

}
