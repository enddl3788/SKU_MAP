package com.yebin.SKUMAP;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class StaffCafeteriaActivity extends Activity {
    String crawlingText = "";
    private TextView staff_cafeteria_mon, staff_cafeteria_tue, staff_cafeteria_wed, staff_cafeteria_thu, staff_cafeteria_fri,
            lunch_mon, lunch_tue, lunch_wed, lunch_thu, lunch_fri, dinner_mon, dinner_tue, dinner_wed, dinner_thu, dinner_fri;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_staff_cafeteria); //xml , java 소스 연결

        final Bundle bundle = new Bundle();


        staff_cafeteria_mon = findViewById(R.id.staff_cafeteria_mon);
        staff_cafeteria_tue = findViewById(R.id.staff_cafeteria_tue);
        staff_cafeteria_wed = findViewById(R.id.staff_cafeteria_wed);
        staff_cafeteria_thu = findViewById(R.id.staff_cafeteria_thu);
        staff_cafeteria_fri = findViewById(R.id.staff_cafeteria_fri);

        lunch_mon = findViewById(R.id.lunch_mon);
        lunch_tue = findViewById(R.id.lunch_tue);
        lunch_wed = findViewById(R.id.lunch_wed);
        lunch_thu = findViewById(R.id.lunch_thu);
        lunch_fri = findViewById(R.id.lunch_fri);

        dinner_mon = findViewById(R.id.dinner_mon);
        dinner_tue = findViewById(R.id.dinner_tue);
        dinner_wed = findViewById(R.id.dinner_wed);
        dinner_thu = findViewById(R.id.dinner_thu);
        dinner_fri = findViewById(R.id.dinner_fri);


        new Thread(){
            @Override
            public void run() {
                Document doc = null;
                try {

                    doc = Jsoup.connect("https://www.sungkyul.ac.kr/skukr/341/subview.do?").get();
                    //Elements table = doc.select(".box-table");
                    Element mon = doc.select("#viewForm > div > table > thead > tr > th:nth-child(2)").first();
                    crawlingText = mon.text().replace(" ", "\n");
                    bundle.putString("mon", crawlingText);                               //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                    Element tue = doc.select("#viewForm > div > table > thead > tr > th:nth-child(3)").first();
                    crawlingText = tue.text().replace(" ", "\n");
                    bundle.putString("tue", crawlingText);
                    Element wed = doc.select("#viewForm > div > table > thead > tr > th:nth-child(4)").first();
                    crawlingText = wed.text().replace(" ", "\n");
                    bundle.putString("wed", crawlingText);
                    Element thu = doc.select("#viewForm > div > table > thead > tr > th:nth-child(5)").first();
                    crawlingText = thu.text().replace(" ", "\n");
                    bundle.putString("thu", crawlingText);
                    Element fri = doc.select("#viewForm > div > table > thead > tr > th:nth-child(6)").first();
                    crawlingText = fri.text().replace(" ", "\n");
                    bundle.putString("fri", crawlingText);

                    Element lunch_mon = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(2)").first();
                    crawlingText = lunch_mon.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("lunch_mon", crawlingText);
                    Element lunch_tue = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(3)").first();
                    crawlingText = lunch_tue.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("lunch_tue", crawlingText);
                    Element lunch_wed = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(4)").first();
                    crawlingText = lunch_wed.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("lunch_wed", crawlingText);
                    Element lunch_thu = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(5)").first();
                    crawlingText = lunch_thu.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("lunch_thu", crawlingText);
                    Element lunch_fri = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(6)").first();
                    crawlingText = lunch_fri.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("lunch_fri", crawlingText);

                    Element dinner_mon = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(2)").first();
                    crawlingText = dinner_mon.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("dinner_mon", crawlingText);
                    Element dinner_tue = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(3)").first();
                    crawlingText = dinner_tue.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("dinner_tue", crawlingText);
                    Element dinner_wed = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(4)").first();
                    crawlingText = dinner_wed.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("dinner_wed", crawlingText);
                    Element dinner_thu = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(5)").first();
                    crawlingText = dinner_thu.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("dinner_thu", crawlingText);
                    Element dinner_fri = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(6)").first();
                    crawlingText = dinner_fri.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("dinner_fri", crawlingText);

                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            staff_cafeteria_mon.setText(replaceSpacesWithNewLine(bundle.getString("mon")));
            staff_cafeteria_tue.setText(replaceSpacesWithNewLine(bundle.getString("tue")));
            staff_cafeteria_wed.setText(replaceSpacesWithNewLine(bundle.getString("wed")));
            staff_cafeteria_thu.setText(replaceSpacesWithNewLine(bundle.getString("thu")));
            staff_cafeteria_fri.setText(replaceSpacesWithNewLine(bundle.getString("fri")));

            lunch_mon.setText(replaceSpacesWithNewLine(bundle.getString("lunch_mon")));
            lunch_tue.setText(replaceSpacesWithNewLine(bundle.getString("lunch_tue")));
            lunch_wed.setText(replaceSpacesWithNewLine(bundle.getString("lunch_wed")));
            lunch_thu.setText(replaceSpacesWithNewLine(bundle.getString("lunch_thu")));
            lunch_fri.setText(replaceSpacesWithNewLine(bundle.getString("lunch_fri")));

            dinner_mon.setText(replaceSpacesWithNewLine(bundle.getString("dinner_mon")));
            dinner_tue.setText(replaceSpacesWithNewLine(bundle.getString("dinner_tue")));
            dinner_wed.setText(replaceSpacesWithNewLine(bundle.getString("dinner_wed")));
            dinner_thu.setText(replaceSpacesWithNewLine(bundle.getString("dinner_thu")));
            dinner_fri.setText(replaceSpacesWithNewLine(bundle.getString("dinner_fri")));
        }
    };
    public String replaceSpacesWithNewLine(String inputString) {
        String outputString = inputString.replaceAll("\\s+", "\n");
        return outputString;
    }
}
