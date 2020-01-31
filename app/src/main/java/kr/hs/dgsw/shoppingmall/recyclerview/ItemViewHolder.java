package kr.hs.dgsw.shoppingmall.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.hs.dgsw.shoppingmall.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView itemText;
    ImageView itemImage;
    TextView priceText;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemText = itemView.findViewById(R.id.itemText);
        itemImage = itemView.findViewById(R.id.itemImage);
        priceText = itemView.findViewById(R.id.priceText);
    }

}
