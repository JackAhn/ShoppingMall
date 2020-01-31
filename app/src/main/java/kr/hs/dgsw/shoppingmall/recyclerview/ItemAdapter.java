package kr.hs.dgsw.shoppingmall.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import kr.hs.dgsw.shoppingmall.R;
import kr.hs.dgsw.shoppingmall.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    ArrayList<Item> itemList;
    ItemClickListener listener;

    public ItemAdapter(ArrayList<Item> itemList, ItemClickListener listener){
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemText.setText(item.getName());
        holder.priceText.setText(new DecimalFormat("#,##0원").format(item.getPrice()));
        final int index = position;
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(v, index);
        });
    }

    @Override
    public int getItemCount() {
        if(itemList == null)
            return 0;
        else
            return itemList.size();
    }
}
