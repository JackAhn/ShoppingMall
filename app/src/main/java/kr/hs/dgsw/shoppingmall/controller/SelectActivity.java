package kr.hs.dgsw.shoppingmall.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kr.hs.dgsw.shoppingmall.R;
import kr.hs.dgsw.shoppingmall.model.Item;
import kr.hs.dgsw.shoppingmall.model.ItemDBHelpler;
import kr.hs.dgsw.shoppingmall.model.UserDBHelper;

public class SelectActivity extends AppCompatActivity {

    public static UserDBHelper userDBHelper;
    public static ItemDBHelpler itemDBHelpler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        userDBHelper = new UserDBHelper(this, "userdb", null, 1);
        itemDBHelpler = new ItemDBHelpler(this, "itemdb", null, 1);
        Intent intent = new Intent(this, LoadingActivity.class);

        itemDBHelpler.resetDB();
        itemDBHelpler.createDB();
        SetShoppingItem();

        startActivity(intent);
    }

    public void evLogin(View v){
        Log.i("Select", "Login Click");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void evGoRegister(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    //쇼핑몰 아이템 추가(default)
    private void SetShoppingItem(){
        Item item = new Item();
        item.setName("Test");
        item.setPrice(1000);
        itemDBHelpler.insertDB(item);
    }
}
