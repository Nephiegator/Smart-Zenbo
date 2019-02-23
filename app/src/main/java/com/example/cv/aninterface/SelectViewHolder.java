package com.example.cv.aninterface;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;


public class SelectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    CheckBox box;
    TextView title, desc, loc, per;
    ItemClickListener itemClickListener;

    public SelectViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title_task_select);
        desc = itemView.findViewById(R.id.desc_task_select);
        loc = itemView.findViewById(R.id.loc_task_select);
        per = itemView.findViewById(R.id.per_task_select);
        //box = itemView.findViewById(R.id.checkBox);

        box.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener= ic;
    }


    @Override
    public void onClick(View v) {
        this.itemClickListener.OnItemClick(v,getLayoutPosition());

    }
}
