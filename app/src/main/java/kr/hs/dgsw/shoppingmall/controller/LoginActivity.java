package kr.hs.dgsw.shoppingmall.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import kr.hs.dgsw.shoppingmall.R;
import kr.hs.dgsw.shoppingmall.model.User;
import kr.hs.dgsw.shoppingmall.model.UserDBHelper;

import static kr.hs.dgsw.shoppingmall.controller.SelectActivity.userDBHelper;

public class LoginActivity extends AppCompatActivity {

    private TextView idText, pwText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        idText = (TextView) findViewById(R.id.useridText);
        pwText = (TextView) findViewById(R.id.userpwText);
    }

    public void evCheckLogin(View v){
        String id = idText.getText().toString();
        String pw = pwText.getText().toString();

        User resultUser = userDBHelper.getUser(id, pw);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(resultUser != null){
            builder.setTitle("로그인").setMessage(resultUser.getName() + "님 환영합니다.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else{
            builder.setTitle("로그인 에러").setMessage("아이디 또는 비밀번호를 확인해주세요.");
            builder.setPositiveButton("OK", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void evBack(View v){
        finish();
    }
}
