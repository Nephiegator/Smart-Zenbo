package com.example.cv.aninterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainplan);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new MainAdapter();
        adapter.setItemList(createItem());
        recyclerView.setAdapter(adapter);
    }

    private List<BaseItem> createItem() {
        List<BaseItem> itemList = new ArrayList<>();
        /*itemList.add(new CardViewItem()
                .setCardViewText("Hello World"));
        itemList.add(new CardViewItem()
                .setCardViewImage(R.mipmap.ic_launcher_round)
                .setCardViewText("Hello RecyclerView"));
        itemList.add(new CardViewItem()
                .setCardViewImage(R.mipmap.ic_launcher)
                .setCardViewText("Hello Android"));*/
        return itemList;
    }
}
