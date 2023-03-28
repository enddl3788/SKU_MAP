package com.yebin.SKUMAP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatDelegate;

public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading); //xml , java 소스 연결
        Handler handler = new Handler();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MapviewActivity.class);
                startActivity(intent); //다음화면으로 넘어감
                finish();
            }
        }, 2000); //2초 뒤에 Runner 객체 실행하도록 함
    }
    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //다음 화면에 실행할 애니메이션, 현재 화면에 실행할 애니메이션
        finish();
    }
}
