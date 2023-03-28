package com.yebin.SKUMAP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class ImageActivity extends Activity {

    private ImageView image_out;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image); //xml , java 소스 연결
        Intent intent = getIntent();

        int x = intent.getIntExtra("image", 0);
        ImageView image_out = (ImageView)findViewById(R.id.image_out);
        if (x == 0){
            image_out.setImageResource(R.drawable.bus_time);
        }

    }
    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //다음 화면에 실행할 애니메이션, 현재 화면에 실행할 애니메이션
    }
}