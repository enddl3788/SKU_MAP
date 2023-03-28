package com.yebin.SKUMAP.Info;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.yebin.SKUMAP.R;

public class Info_copy extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info_copy); //xml , java 소스 연결
    }
}
