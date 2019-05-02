package com.example.cv.aninterface;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.histaskViewHolder> {

    private Context mCtx;
    private List<dbHistory> histasklist;


    public HistoryAdapter(Context mCtx, List<dbHistory> histasklist) {
        this.mCtx = mCtx;
        this.histasklist = histasklist;
    }


    @NonNull
    @Override
    public HistoryAdapter.histaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAdapter.histaskViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.list_history, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.histaskViewHolder holder, int position) {
        dbHistory his = histasklist.get(position);

        holder.textview_his_title.setText(his.getTitle());
        holder.textview_his_time.setText(his.getTime());
        holder.textview_his_verify.setText(his.getVerify());

    }

    @Override
    public int getItemCount() {
        return histasklist.size();
    }

    class histaskViewHolder extends RecyclerView.ViewHolder {
        TextView textview_his_title, textview_his_time, textview_his_verify;


        public histaskViewHolder(View itemView) {
            super(itemView);

            textview_his_title = itemView.findViewById(R.id.textView_his_title);
            textview_his_time = itemView.findViewById(R.id.textView_his_time);
            textview_his_verify = itemView.findViewById(R.id.textView_his_verify);

            //itemView.setOnClickListener(this);

        }
    }
}
