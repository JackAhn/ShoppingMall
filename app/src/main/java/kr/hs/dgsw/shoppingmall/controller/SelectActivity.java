package kr.hs.dgsw.shoppingmall.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kr.hs.dgsw.shoppingmall.R;
import kr.hs.dgsw.shoppingmall.model.UserDBHelper;

public class SelectActivity extends AppCompatActivity {

    public static UserDBHelper userDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        userDBHelper = new UserDBHelper(this, "userdb", null, 1);
        Intent intent = new Intent(this, LoadingActivity.class);
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
}
