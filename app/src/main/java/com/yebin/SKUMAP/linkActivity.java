package com.yebin.SKUMAP;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class linkActivity extends AppCompatActivity {

    private Button btn_link_1, btn_link_2, btn_link_3, btn_link_4, btn_link_5, btn_link_6, btn_link_7, btn_link_8, btn_link_9, btn_link_10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link); //xml , java 소스 연결

        btn_link_1 = findViewById(R.id.btn_link_1);
        btn_link_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sky.sungkyul.ac.kr:444/websquare/websquare.html?w2xPath=/scr/system/main.xml"));
                startActivity(intent);
            }
        });

        btn_link_2 = findViewById(R.id.btn_link_2);
        btn_link_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cc.sungkyul.ac.kr/login.php?"));
                startActivity(intent);
            }
        });

        btn_link_3 = findViewById(R.id.btn_link_3);
        btn_link_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.sungkyul.ac.kr/"));
                startActivity(intent);
            }
        });

        btn_link_4 = findViewById(R.id.btn_link_4);
        btn_link_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://success.sungkyul.ac.kr/career/"));
                startActivity(intent);
            }
        });

        btn_link_5 = findViewById(R.id.btn_link_5);
        btn_link_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sungkyul.ac.kr/"));
                startActivity(intent);
            }
        });

        btn_link_6 = findViewById(R.id.btn_link_6);
        btn_link_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://library.sungkyul.ac.kr/lib/SlimaPlus.csp#link"));
                startActivity(intent);
            }
        });

        btn_link_7 = findViewById(R.id.btn_link_7);
        btn_link_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sungkyul.ac.kr/skukr/313/subview.do"));
                startActivity(intent);
            }
        });

        btn_link_8 = findViewById(R.id.btn_link_8);
        btn_link_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sungkyul.webminwon.kr/"));
                startActivity(intent);
            }
        });

        btn_link_9 = findViewById(R.id.btn_link_9);
        btn_link_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sungkyul.ac.kr/skukr/343/subview.do?"));
                startActivity(intent);
            }
        });

        btn_link_10 = findViewById(R.id.btn_link_10);
        btn_link_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sungkyul.ac.kr/portalLogin/skukr/portalPage.do"));
                startActivity(intent);
            }
        });
    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //다음 화면에 실행할 애니메이션, 현재 화면에 실행할 애니메이션
    }
}
