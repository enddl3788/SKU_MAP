package com.yebin.SKUMAP;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yebin.SKUMAP.Info.Info_parkingGuide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class menuActivity extends AppCompatActivity {

    String crawlingText = "";
    private TextView menu_temp;
    private ImageButton btn_menu_to_mapview, btn_menu_to_cafeteria, btn_menu_to_bus, btn_menu_to_link, btn_menu_to_parking, btn_menu_to_option;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu); //xml , java 소스 연결

        final Bundle bundle = new Bundle();

        menu_temp = findViewById(R.id.menu_temp);

        new Thread(){
            @Override
            public void run() {
                Document doc = null;
                try {

                    doc = Jsoup.connect("https://www.google.com/search?q=%EC%95%88%EC%96%91%EC%8B%9C+%EB%A7%8C%EC%95%88%EA%B5%AC+%EB%82%A0%EC%94%A8").get();
                    //Elements table = doc.select(".box-table");
                    Element temp = doc.select("#wob_tm").first();
                    crawlingText = temp.text();
                    bundle.putString("temp", crawlingText);                               //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.

                    Element rain = doc.select("#wob_pp").first();
                    crawlingText = rain.text();
                    bundle.putString("rain", crawlingText);

                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        btn_menu_to_mapview = findViewById(R.id.btn_menu_to_mapview);
        btn_menu_to_mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_menu_to_cafeteria = findViewById(R.id.btn_menu_to_cafeteria);
        btn_menu_to_cafeteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CafeteriaActivity.class);
                startActivity(intent);
            }
        });

        btn_menu_to_bus = findViewById(R.id.btn_menu_to_bus);
        btn_menu_to_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                intent.putExtra("image", 0);
                startActivity(intent);
            }
        });

        btn_menu_to_link = findViewById(R.id.btn_menu_to_link);
        btn_menu_to_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), linkActivity.class);
                startActivity(intent);
            }
        });

        btn_menu_to_parking = findViewById(R.id.btn_menu_to_parking);
        btn_menu_to_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Info_parkingGuide.class);
                startActivity(intent);
            }
        });

        btn_menu_to_option = findViewById(R.id.btn_menu_to_option);
        btn_menu_to_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(menuActivity.this,"개발중", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //다음 화면에 실행할 애니메이션, 현재 화면에 실행할 애니메이션
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String temp = bundle.getString("temp");
            String rain = bundle.getString("rain");
            menu_temp.setText("온도 : " + temp + "°C 강수확률 : " + rain);
        }
    };
}

