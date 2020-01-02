package kr.hs.dgsw.shoppingmall.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import kr.hs.dgsw.shoppingmall.R;

public class LoadingActivity extends AppCompatActivity {

    ImageView im = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        im = (ImageView) findViewById(R.id.imageView);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(im);
        Glide.with(this).load(R.drawable.loading_icon).into(gifImage);

        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
                finish();
        }, 3000);
    }
}
