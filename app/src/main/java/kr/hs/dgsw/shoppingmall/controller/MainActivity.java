package kr.hs.dgsw.shoppingmall.controller;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hs.dgsw.shoppingmall.R;
import kr.hs.dgsw.shoppingmall.model.Item;
import kr.hs.dgsw.shoppingmall.recyclerview.ItemAdapter;
import kr.hs.dgsw.shoppingmall.recyclerview.ItemClickListener;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private ArrayList<Item> itemList;
    private RecyclerView mainItemView;
    private ItemAdapter itemAdapter;
    private GridLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList = SelectActivity.itemDBHelpler.getAllItems();

        itemAdapter = new ItemAdapter(itemList, this);
        mainItemView = findViewById(R.id.MainItemList);

        layoutManager = new GridLayoutManager(this, 3);
        mainItemView.setLayoutManager(layoutManager);
        mainItemView.setAdapter(itemAdapter);
    }

    public void evLogout(View v){
        finish();
    }

    //아이템 삭제 뒤 RecyclerView 데이터 갱신
    //itemList.remove(position);
    //adapter.notifyItemRemoved(position);
    //itemAdapter.notifyDataSetChanged();

    //아이템 추가 후 데이터 갱신
    //Item item = new Item()
    //itemList.add(item)
    //itemAdapter.notifyItemInserted(itemList.size() - 1);
    //itemAdapter.notifyDataSetChanged();

    //아이템 변경 후 데이터 갱신
    //Item item = new Item()
    //(아이템 변경 소스 추가)
    //itemList.add(item)
    //itemAdapter.notifyItemInserted(바꾼 아이템 인덱스);
    //itemAdapter.notifyDataSetChanged();

    @Override
    public void onItemClick(View v, int position) {
        itemAdapter.notifyDataSetChanged();
    }
}
