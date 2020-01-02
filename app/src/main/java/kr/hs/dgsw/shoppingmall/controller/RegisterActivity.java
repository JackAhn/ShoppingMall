package kr.hs.dgsw.shoppingmall.controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

import kr.hs.dgsw.shoppingmall.R;
import kr.hs.dgsw.shoppingmall.model.User;

import static kr.hs.dgsw.shoppingmall.controller.SelectActivity.userDBHelper;

public class RegisterActivity extends AppCompatActivity {

    private RadioButton manButton, womanButton, teenButton, twentyButton, thirtyButton;
    private TextView nameText, idText, pwText, checkPWText;
    private int genderno = 0;
    private int ageno = 0;

    private void genderClick(View v){
        RadioButton selBtn = (RadioButton)v;
        switch(selBtn.getText().toString()){
            case "남":
                genderno = 1;
                break;
            case "여":
                genderno = 2;
                break;
        }
    }

    private void ageClick(View v){
        RadioButton selBtn = (RadioButton)v;
        switch(selBtn.getText().toString()){
            case "10대":
                ageno = 1;
                break;
            case "20대":
                ageno = 2;
                break;
            case "30대 이상":
                ageno = 3;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        manButton = (RadioButton) findViewById(R.id.manButton);
        womanButton = (RadioButton) findViewById(R.id.womanButton);
        manButton.setOnClickListener(this::genderClick);
        womanButton.setOnClickListener(this::genderClick);

        teenButton = (RadioButton) findViewById(R.id.teenButton);
        twentyButton = (RadioButton) findViewById(R.id.twentyButton);
        thirtyButton = (RadioButton) findViewById(R.id.thirtyButton);
        teenButton.setOnClickListener(this::ageClick);
        twentyButton.setOnClickListener(this::ageClick);
        thirtyButton.setOnClickListener(this::ageClick);

        nameText = (TextView) findViewById(R.id.regisNameText);
        idText = (TextView) findViewById(R.id.regisIDText);
        pwText = (TextView) findViewById(R.id.regisPWText);
        checkPWText = (TextView) findViewById(R.id.regisCheckPWText);
    }

    public void evRegisBack(View v){
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void evCheckRegister(View v){
        String[] data = {
                nameText.getText().toString().trim(),
                idText.getText().toString().trim(),
                pwText.getText().toString().trim(),
                checkPWText.getText().toString().trim()
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int cnt = (int) Arrays.stream(data).filter(x -> x.getBytes().length <= 0).count();
        Log.i("Register", cnt + "");
        if(cnt > 0){
            builder.setTitle("회원가입 에러").setMessage("공백이 존재합니다.");
            builder.setPositiveButton("OK", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }

        if(pwText.getText().toString().equals(checkPWText.getText().toString()) == false){
            builder.setTitle("회원가입 에러").setMessage("비밀번호를 확인해주세요.");
            builder.setPositiveButton("OK", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }

        if(genderno == 0){
            builder.setTitle("회원가입 에러").setMessage("성별을 선택해주세요.");
            builder.setPositiveButton("OK", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }
        else if(ageno == 0){
            builder.setTitle("회원가입 에러").setMessage("나이를 선택해주세요.");
            builder.setPositiveButton("OK", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }

        User sendUser = new User();
        sendUser.setId(0);
        sendUser.setName(data[0]);
        sendUser.setUserId(data[1]);
        sendUser.setUserPw(data[2]);
        sendUser.setGender(genderno);
        sendUser.setAge(ageno);
        userDBHelper.insertDB(sendUser);
        builder.setTitle("회원가입").setMessage("회원가입이 완료되었습니다.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
