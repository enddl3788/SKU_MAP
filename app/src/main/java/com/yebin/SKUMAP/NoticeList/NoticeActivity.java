package com.yebin.SKUMAP.NoticeList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.yebin.SKUMAP.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoticeActivity extends Activity {
    public ListView listView;
    public NoticeListItemAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String mainText, subText, crawlingText;
    private String Tag = "NoticeActivity";
    private String URL = ("https://www.sungkyul.ac.kr/skukr/313/subview.do?");
    private TextView noticeTitle;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notice); //xml , java 소스 연결

        listView = findViewById(R.id.notice_list);
        noticeTitle = findViewById(R.id.noticeTitle);

        final Bundle bundle = new Bundle();

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(URL).get();

                    Element mainTextElements = document.select("#schdulWrap > div.alb-body > div.sche-comt > table > tbody").first();
                    crawlingText = mainTextElements.text();
                    bundle.putString("mainTextElements", crawlingText);

                    Element TitleElements = document.select("#schdulWrap > div.alb-body > div.sche-comt > table > caption").first();
                    crawlingText = TitleElements.text().replace(" - 날짜, 일정내용", "");;
                    bundle.putString("TitleElements", crawlingText);

                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            mainText = bundle.getString("mainTextElements");
            parseDateDescription(mainText);
            noticeTitle.setText(bundle.getString("TitleElements"));
        }
    };

    public void parseDateDescription(String input) {
        // 정규 표현식 패턴
        String pattern = "(\\d{2}\\.\\d{2}) ~ (\\d{2}\\.\\d{2}) (.+?)(?=(\\d{2}\\.\\d{2} ~|$))";

        // 패턴을 컴파일
        Pattern r = Pattern.compile(pattern);

        // Matcher 객체 생성
        Matcher matcher = r.matcher(input);

        // 현재 날짜 가져오기
        Date currentDate = new Date();

        // 날짜 포맷 설정 (MM.dd 형식)
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd");

        NoticeListItemAdapter adapter;
        adapter = new NoticeListItemAdapter();

        // 어댑터를 리스트뷰에 연결
        listView.setAdapter(adapter);

        // 반복문을 사용하여 일치하는 패턴을 찾고 출력
        while (matcher.find()) {
            String startDate = matcher.group(1); // 시작 날짜
            String endDate = matcher.group(2);   // 종료 날짜
            String description = matcher.group(3); // 설명

            try {
                // 문자열 형식의 날짜를 Date 객체로 파싱
                Date startDateParsed = dateFormat.parse(startDate);
                Date endDateParsed = dateFormat.parse(endDate);

                // 현재 날짜와 시작 날짜, 종료 날짜를 비교
                if (currentDate.compareTo(startDateParsed) >= 0 && currentDate.compareTo(endDateParsed) <= 0) {
                    // 현재 날짜가 시작 날짜와 종료 날짜 사이에 있는 경우
                    adapter.addItem(new NoticeListItem("[TODAY] " + description, startDate + "~" + endDate));
                } else {
                    adapter.addItem(new NoticeListItem(description, startDate + "~" + endDate));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 어댑터에 데이터가 변경되었음을 알림
        adapter.notifyDataSetChanged();
    }
}