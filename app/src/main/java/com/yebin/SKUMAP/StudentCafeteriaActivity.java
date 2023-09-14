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

public class StudentCafeteriaActivity extends Activity {
    String crawlingText = "";
    private TextView student_cafeteria_mon, student_cafeteria_tue, student_cafeteria_wed, student_cafeteria_thu, student_cafeteria_fri,
            RN_mon, RN_tue, RN_wed, RN_thu, RN_fri, Tb_mon, Tb_tue, Tb_wed, Tb_thu, Tb_fri, FR_mon, FR_tue, FR_wed, FR_thu, FR_fri;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_student_cafeteria); //xml , java 소스 연결

        final Bundle bundle = new Bundle();


        student_cafeteria_mon = findViewById(R.id.student_cafeteria_mon);
        student_cafeteria_tue = findViewById(R.id.student_cafeteria_tue);
        student_cafeteria_wed = findViewById(R.id.student_cafeteria_wed);
        student_cafeteria_thu = findViewById(R.id.student_cafeteria_thu);
        student_cafeteria_fri = findViewById(R.id.student_cafeteria_fri);

        RN_mon = findViewById(R.id.RN_mon);
        RN_tue = findViewById(R.id.RN_tue);
        RN_wed = findViewById(R.id.RN_wed);
        RN_thu = findViewById(R.id.RN_thu);
        RN_fri = findViewById(R.id.RN_fri);

        Tb_mon = findViewById(R.id.Tb_mon);
        Tb_tue = findViewById(R.id.Tb_tue);
        Tb_wed = findViewById(R.id.Tb_wed);
        Tb_thu = findViewById(R.id.Tb_thu);
        Tb_fri = findViewById(R.id.Tb_fri);

        FR_mon = findViewById(R.id.FR_mon);
        FR_tue = findViewById(R.id.FR_tue);
        FR_wed = findViewById(R.id.FR_wed);
        FR_thu = findViewById(R.id.FR_thu);
        FR_fri = findViewById(R.id.FR_fri);


        new Thread(){
            @Override
            public void run() {
                Document doc = null;
                try {

                    doc = Jsoup.connect("https://www.sungkyul.ac.kr/skukr/340/subview.do?").get();
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

                    Element RN_mon = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(2)").first();
                    crawlingText = RN_mon.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("RN_mon", crawlingText);
                    Element RN_tue = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(3)").first();
                    crawlingText = RN_tue.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("RN_tue", crawlingText);
                    Element RN_wed = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(4)").first();
                    crawlingText = RN_wed.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("RN_wed", crawlingText);
                    Element RN_thu = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(5)").first();
                    crawlingText = RN_thu.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("RN_thu", crawlingText);
                    Element RN_fri = doc.select("#viewForm > div > table > tbody > tr:nth-child(1) > td:nth-child(6)").first();
                    crawlingText = RN_fri.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("RN_fri", crawlingText);

                    Element Tb_mon = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(2)").first();
                    crawlingText = Tb_mon.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("Tb_mon", crawlingText);
                    Element Tb_tue = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(3)").first();
                    crawlingText = Tb_tue.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("Tb_tue", crawlingText);
                    Element Tb_wed = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(4)").first();
                    crawlingText = Tb_wed.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("Tb_wed", crawlingText);
                    Element Tb_thu = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(5)").first();
                    crawlingText = Tb_thu.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("Tb_thu", crawlingText);
                    Element Tb_fri = doc.select("#viewForm > div > table > tbody > tr:nth-child(2) > td:nth-child(6)").first();
                    crawlingText = Tb_fri.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("Tb_fri", crawlingText);

                    Element FR_mon = doc.select("#viewForm > div > table > tbody > tr:nth-child(3) > td:nth-child(2)").first();
                    crawlingText = FR_mon.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("FR_mon", crawlingText);
                    Element FR_tue = doc.select("#viewForm > div > table > tbody > tr:nth-child(3) > td:nth-child(3)").first();
                    crawlingText = FR_tue.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("FR_tue", crawlingText);
                    Element FR_wed = doc.select("#viewForm > div > table > tbody > tr:nth-child(3) > td:nth-child(4)").first();
                    crawlingText = FR_wed.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("FR_wed", crawlingText);
                    Element FR_thu = doc.select("#viewForm > div > table > tbody > tr:nth-child(3) > td:nth-child(5)").first();
                    crawlingText = FR_thu.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("FR_thu", crawlingText);
                    Element FR_fri = doc.select("#viewForm > div > table > tbody > tr:nth-child(3) > td:nth-child(6)").first();
                    crawlingText = FR_fri.text().replace("등록된 식단내용이(가) 없습니다.", "\n정보없음\n");
                    crawlingText.replace(" ", "\n");
                    bundle.putString("FR_fri", crawlingText);

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
            student_cafeteria_mon.setText(replaceSpacesWithNewLine(bundle.getString("mon")));
            student_cafeteria_tue.setText(replaceSpacesWithNewLine(bundle.getString("tue")));
            student_cafeteria_wed.setText(replaceSpacesWithNewLine(bundle.getString("wed")));
            student_cafeteria_thu.setText(replaceSpacesWithNewLine(bundle.getString("thu")));
            student_cafeteria_fri.setText(replaceSpacesWithNewLine(bundle.getString("fri")));

            RN_mon.setText(replaceSpacesWithNewLine(bundle.getString("RN_mon")));
            RN_tue.setText(replaceSpacesWithNewLine(bundle.getString("RN_tue")));
            RN_wed.setText(replaceSpacesWithNewLine(bundle.getString("RN_wed")));
            RN_thu.setText(replaceSpacesWithNewLine(bundle.getString("RN_thu")));
            RN_fri.setText(replaceSpacesWithNewLine(bundle.getString("RN_fri")));

            Tb_mon.setText(replaceSpacesWithNewLine(bundle.getString("Tb_mon")));
            Tb_tue.setText(replaceSpacesWithNewLine(bundle.getString("Tb_tue")));
            Tb_wed.setText(replaceSpacesWithNewLine(bundle.getString("Tb_wed")));
            Tb_thu.setText(replaceSpacesWithNewLine(bundle.getString("Tb_thu")));
            Tb_fri.setText(replaceSpacesWithNewLine(bundle.getString("Tb_fri")));

            FR_mon.setText(replaceSpacesWithNewLine(bundle.getString("FR_mon")));
            FR_tue.setText(replaceSpacesWithNewLine(bundle.getString("FR_tue")));
            FR_wed.setText(replaceSpacesWithNewLine(bundle.getString("FR_wed")));
            FR_thu.setText(replaceSpacesWithNewLine(bundle.getString("FR_thu")));
            FR_fri.setText(replaceSpacesWithNewLine(bundle.getString("FR_fri")));
        }
    };

    public String replaceSpacesWithNewLine(String inputString) {
        String outputString = inputString.replaceAll("\\s+", "\n");
        return outputString;
    }
}
