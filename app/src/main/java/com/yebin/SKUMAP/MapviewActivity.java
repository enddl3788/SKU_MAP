package com.yebin.SKUMAP;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yebin.SKUMAP.Info.Info_book;
import com.yebin.SKUMAP.Info.Info_copy;
import com.yebin.SKUMAP.Info.Info_hsh;
import com.yebin.SKUMAP.Info.Info_hsjb;
import com.yebin.SKUMAP.Info.Info_jl;
import com.yebin.SKUMAP.Info.Info_js;
import com.yebin.SKUMAP.Info.Info_kn;
import com.yebin.SKUMAP.Info.Info_sk;
import com.yebin.SKUMAP.Info.Info_su;
import com.yebin.SKUMAP.Info.Info_uch;
import com.yebin.SKUMAP.Info.Info_ya;
import com.yebin.SKUMAP.MapviewList.ListItem;
import com.yebin.SKUMAP.MapviewList.ListItemAdapter;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;

public class MapviewActivity extends AppCompatActivity {
    private ImageButton btn_main_to_facility,btn_main_to_dijkstra, btn_main_to_menu;
    private ListView listView;
    private ListItemAdapter adapter;
    private ScrollView sv_facility, sv_dijkstra;
    private LinearLayout list_topBar;
    private Button btn_main_result;
    private TextView tv_main_result, tv_main_route, tv_notice;
    private Spinner s_main_start, s_main_end;

    public static MapView.MapType Standard;
    public static MapView.MapType Satellite;
    public static MapView.MapType Hybrid;

    public MapView mapView;
    public static Context mContext;
    public MapPOIItem jl,hsjb,kn,js,ya,hsh,su,uch,book,copy,sk,jm;

    public MapPoint[] facility_MP = {sk_mp, jl_mp, hsjb_mp, kn_mp, js_mp, ya_mp, hsh_mp, su_mp, uch_mp, book_mp, copy_mp};
    public MapPOIItem[] facility_MPI = {sk, jl, hsjb, kn, js, ya, hsh, su, uch, book, copy};
    public String[] facility = {"성결관","재림관","학술정보관","기념관","중생관","영암관","학생회관","신유관","유치원","서점","복사실"};
    private String Tag = "MapviewActivity";

    private static final MapPoint jl_mp = MapPoint.mapPointWithGeoCoord(37.380333214324104, 126.92768699975836);
    private static final MapPoint hsjb_mp = MapPoint.mapPointWithGeoCoord(37.38048803968827, 126.92671018055707);
    private static final MapPoint kn_mp = MapPoint.mapPointWithGeoCoord(37.37935335097545, 126.92768794057727);
    private static final MapPoint js_mp = MapPoint.mapPointWithGeoCoord(37.378901481588066, 126.92916746282529);
    private static final MapPoint ya_mp = MapPoint.mapPointWithGeoCoord(37.37923074237307, 126.92981637419574);
    private static final MapPoint hsh_mp = MapPoint.mapPointWithGeoCoord(37.37952116986018, 126.92955923685072);
    private static final MapPoint su_mp = MapPoint.mapPointWithGeoCoord(37.380994547739455, 126.9261987669591);
    private static final MapPoint uch_mp = MapPoint.mapPointWithGeoCoord(37.38053214661729, 126.928852604042);
    private static final MapPoint book_mp = MapPoint.mapPointWithGeoCoord(37.38172414754555, 126.92951483441296);
    private static final MapPoint copy_mp = MapPoint.mapPointWithGeoCoord(37.37994168983829, 126.9283789437447);
    private static final MapPoint sk_mp = MapPoint.mapPointWithGeoCoord(37.37985860929188, 126.92881654491944);
    private static final MapPoint jm_mp = MapPoint.mapPointWithGeoCoord(37.381270929916795, 126.9287587544689);

    public MapPOIItem[] spiner_MPI = {hsjb, hsh, jl, js, ya, kn, sk, jm};
    public MapPoint[] spiner_MP = {hsjb_mp, hsh_mp, jl_mp, js_mp, ya_mp, kn_mp, sk_mp, jm_mp};
    private String[] spinerItems = {"학술정보관", "학생회관", "재림관", "중생관", "영암관", "기념관", "성결관", "정문"};
    private int[] spinerScore = {16, 5, 15, 9, 8, 12, 4, 0};

    private int end, start, spinerStart, spinerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview); //xml , java 소스 연결

        getAppKeyHash();

        final int[] mapType = {1};

        mapView = new MapView(this);

        mContext = this;

        listView = findViewById(R.id.facility_list);
        sv_dijkstra = findViewById(R.id.sv_dijkstra);
        sv_facility = findViewById(R.id.sv_facility);

        adapter = new ListItemAdapter();

        for(int i = 0; i < facility.length; i++){
            adapter.addItem(new ListItem(facility[i]));
        }
        listView.setAdapter(adapter);

        final int[] sv_onoff = new int[2];
        sv_onoff[0] = 0;
        sv_onoff[1] = 0;
        setParam(0,sv_facility);
        setParam(0,sv_dijkstra);

        Toast.makeText(MapviewActivity.this,"VERSION 7, 2023.01.20", Toast.LENGTH_SHORT).show();

        Dijkstra d = new Dijkstra(17);
        d.input("a","b",60);
        d.input("b","c",60);
        d.input("b","o",60);
        d.input("c","d",10);
        d.input("c","j",150);
        d.input("c","o",60);
        d.input("d","e",30);
        d.input("e","f",60);
        d.input("f","g",80);
        d.input("f","j",90);
        d.input("g","h",0);
        d.input("h","i",20);
        d.input("i","j",30);
        d.input("j","k",60);
        d.input("k","l",60);
        d.input("k","n",120);
        d.input("n","m",60);
        d.input("m","o",40);
        d.input("m","q",70);
        d.input("o","p",10);
        d.input("p","q",90);

        btn_main_to_facility = findViewById(R.id.btn_main_to_facility);
        btn_main_to_facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((sv_onoff[0] == 0)&&(sv_onoff[1] == 0)) {
                    sv_onoff[0] = 1;
                    mapView.setMapCenterPointAndZoomLevel(jl_mp,2 , true);
                    setParam(3,sv_facility);
                    for (int i = 0; i < facility.length; i++) {
                        add_marker(facility_MPI[i], facility[i], facility_MP[i], mapView);
                    }
                }else if((sv_onoff[0] == 0)&&(sv_onoff[1] == 1)){
                    setParam(0,sv_dijkstra);
                    setParam(3,sv_facility);
                    sv_onoff[1] = 0;
                    sv_onoff[0] = 1;
                }else {
                    sv_onoff[0] = 0;
                    setParam(0,sv_facility);
                    mapView.removeAllPOIItems();
                }
            }
        });

        tv_notice = findViewById(R.id.tv_notice);
        tv_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MapviewActivity.this,"점검중", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        btn_main_to_dijkstra = findViewById(R.id.btn_main_to_dijkstra);
        btn_main_to_dijkstra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((sv_onoff[1] == 0)&&(sv_onoff[0] == 0)) {
                    sv_onoff[1] = 1;
                    setParam(3,sv_dijkstra);
                }else if((sv_onoff[1] == 0)&&(sv_onoff[0] == 1)) {
                    setParam(3,sv_dijkstra);
                    setParam(0,sv_facility);
                    sv_onoff[1] = 1;
                    sv_onoff[0] = 0;
                }else {
                    sv_onoff[1] = 0;
                    setParam(0,sv_dijkstra);
                    mapView.removeAllPOIItems();
                }
            }
        });
        btn_main_to_menu = findViewById(R.id.btn_main_to_menu);
        btn_main_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), menuActivity.class);
                startActivity(intent);
            }
        });
        Spinner s_main_start = findViewById(R.id.s_main_start);
        Spinner s_main_end = findViewById(R.id.s_main_end);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s_main_start.setAdapter(adapter);
        s_main_end.setAdapter(adapter);
        s_main_start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                start = spinerScore[i];
                spinerStart = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        s_main_end.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                end = spinerScore[i];
                spinerEnd = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        tv_main_result = findViewById(R.id.tv_main_result);
        tv_main_route = findViewById(R.id.tv_main_route);
        btn_main_result = findViewById(R.id.btn_main_result);
        btn_main_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_main_result.setText("예상 소요시간 : " + d.algorithm(start, end) + "초");
                tv_main_route.setText("경로 : " + stringConversion(d.result_route(start, end)));
                mapView.removeAllPOIItems();
                add_marker(spiner_MPI[spinerStart], spinerItems[spinerStart], spiner_MP[spinerStart], mapView);
                add_marker(spiner_MPI[spinerEnd], spinerItems[spinerEnd], spiner_MP[spinerEnd], mapView);
            }
        });

        FloatingActionButton btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.setMapCenterPointAndZoomLevel(jl_mp,1 , true);
            }
        });
        FloatingActionButton btn_location = findViewById(R.id.btn_location);
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapType[0] > 2) { mapType[0] -= 3; }

                if (mapType[0] == 0) {
                    mapView.setMapType(MapView.MapType.Standard);
                } else if (mapType[0] == 1) {
                    mapView.setMapType(MapView.MapType.Satellite);
                } else if (mapType[0] == 2) {
                    mapView.setMapType(MapView.MapType.Hybrid);
                }
                mapType[0] += 1;
            }
        });

        //지도 중심점 + 줌 레벨
        mapView.setMapCenterPointAndZoomLevel(jl_mp,1 , true);

        RelativeLayout mapViewContainer = (RelativeLayout) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
    }

    @Override
    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //다음 화면에 실행할 애니메이션, 현재 화면에 실행할 애니메이션
    }

    //스크롤뷰 비율 설정
    void setParam(int x, ScrollView y){
        LinearLayout.LayoutParams scroll_param = (LinearLayout.LayoutParams)y.getLayoutParams();
        scroll_param.weight = x;
        y.setLayoutParams(scroll_param);
    }

    private void add_marker(MapPOIItem marker, String name, MapPoint mp, MapView mapView){
        marker = new MapPOIItem();
        marker.setItemName(name);
        marker.setTag(0);
        marker.setMapPoint(mp);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);
    }

    public void removeMarker(){
        ((MapviewActivity)MapviewActivity.mContext).mapView.removeAllPOIItems();
    }

    public void clickEvent(String facility_C){
        for(int i = 0; i < facility.length; i++){
            if (facility_C == facility[i]){
                add_marker(facility_MPI[i], facility[i], facility_MP[i],
                        ((MapviewActivity)MapviewActivity.mContext).mapView);
                ((MapviewActivity)MapviewActivity.mContext).mapView.
                        setMapCenterPointAndZoomLevel(facility_MP[i],1 , true);
            }
        }
    }

    public void btn_clickEvent(String facility_C) {
        if (facility_C == "재림관") {
            Intent intent = new Intent(getApplicationContext(), Info_jl.class);
            startActivity(intent);
        } else if (facility_C == "학술정보관") {
            Intent intent = new Intent(getApplicationContext(), Info_hsjb.class);
            startActivity(intent);
        } else if (facility_C == "기념관") {
            Intent intent = new Intent(getApplicationContext(), Info_kn.class);
            startActivity(intent);
        } else if (facility_C == "중생관") {
            Intent intent = new Intent(getApplicationContext(), Info_js.class);
            startActivity(intent);
        } else if (facility_C == "영암관") {
            Intent intent = new Intent(getApplicationContext(), Info_ya.class);
            startActivity(intent);
        } else if (facility_C == "학생회관") {
            Intent intent = new Intent(getApplicationContext(), Info_hsh.class);
            startActivity(intent);
        } else if (facility_C == "성결관") {
            Intent intent = new Intent(getApplicationContext(), Info_sk.class);
            startActivity(intent);
        } else if (facility_C == "신유관") {
            Intent intent = new Intent(getApplicationContext(), Info_su.class);
            startActivity(intent);
        } else if (facility_C == "유치원") {
            Intent intent = new Intent(getApplicationContext(), Info_uch.class);
            startActivity(intent);
        } else if (facility_C == "서점") {
            Intent intent = new Intent(getApplicationContext(), Info_book.class);
            startActivity(intent);
        } else if (facility_C == "복사실") {
            Intent intent = new Intent(getApplicationContext(), Info_copy.class);
            startActivity(intent);
        }
    }

    public class Dijkstra {
        private int n; //꼭지점 수를 변수로 선언
        private int[][] weight; //2차원 배열 weight에 각 꼭지점의 가중치를 저장
        private String[] saveRoute;
        private String[] vertex = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "n", "m", "o", "p", "q"};

        public Dijkstra(int n) {
            super();
            this.n = n; //생성자를 통해 꼭지점 수를 주입하고,
            weight = new int[n][n]; //가중치를 저장할 배열 weight의 크기 지정.
            saveRoute = new String[n];
        }

        public int stringToInt(String s) {
            //문자열을 int형으로 바꿔준다.
            int x = 0;
            for (int i = 0; i < vertex.length; i++) {
                if (vertex[i] == s) x = i;
            }
            return x;
        }

        public void input(String v1, String v2, int w) {
            //먼저 문자열로 입력된 꼭지점 v1와 v2를 해당되는 숫자 인덱스 x1과 x2로 바꿔준다.
            int x1 = stringToInt(v1);
            int x2 = stringToInt(v2);

            //x1에서 x2까지의 가중치를 주입
            //이 때, x1에서 x2까지의 가중치와 x2에서 x1까지의 가중치는 같다.(중복될 뿐)
            //가중치가 없어서 입력되지 않았다면 기본값 0이 입력되어 있을 것이다.
            weight[x1][x2] = w;
            weight[x2][x1] = w;
        }

        public int algorithm(int a, int b) {
            boolean[] visited = new boolean[n]; //각 꼭지점의 방문 여부
            int distance[] = new int[n]; //시작 꼭지점에서부터 각 꼭지점까지의 거리

            //시작 꼭지점 a에서부터 각 꼭지점까지의 모든 거리 초기화
            for (int i = 0; i < n; i++) {
                //이산수학 교재 251쪽에서는 ∞로 초기화했지만
                //여기에서는 int형의 가장 큰 값 2147483647로 초기화한다.
                distance[i] = Integer.MAX_VALUE;
            }

            int x = a; //문자열로 입력된 시작 꼭지점을 해당되는 숫자 인덱스 x로 바꾸고
            distance[x] = 0; //시작 꼭지점 x의 거리를 0으로 초기화하고,
            visited[x] = true; //방문 꼭지점이므로 true값 저장
            saveRoute[x] = vertex[x]; //★시작 꼭지점의 경로는 자기 자신을 저장

            //시작 꼭지점 x에서부터 꼭지점 i까지의 거리를 갱신한다.
            for (int i = 0; i < n; i++) {
                //방문하지 않았고 x에서 i까지의 가중치가 존재한다면, 거리 i에 x에서 i까지의 가중치 저장
                //즉 x에서 인접한 꼭지점까지의 거리를 갱신함.
                //(x와 인접하지 않은 꼭지점에는 Integer.MAX_VALUE가 계속 저장되어 있을 것이다.)
                if (!visited[i] && weight[x][i] != 0) {
                    distance[i] = weight[x][i];
                    saveRoute[i] = vertex[x]; //★시작 꼭지점과 인접한 꼭지점의 경로에 시작 꼭지점을 저장
                }
            }

            for (int i = 0; i < n - 1; i++) {
                int minDistance = Integer.MAX_VALUE; //최단거리 minDistance에 일단 가장 큰 정수로 저장하고,
                int minVertex = -1; //그 거리값이 있는 인덱스 minIndex에 -1을 저장해둔다.
                for (int j = 0; j < n; j++) {
                    //방문하지 않았고 거리를 갱신한 꼭지점 중에서 가장 가까운 거리와 가장 가까운 꼭지점을 구한다.
                    if (!visited[j] && distance[j] != Integer.MAX_VALUE) {
                        if (distance[j] < minDistance) {
                            minDistance = distance[j];
                            minVertex = j;
                        }
                    }
                }
                visited[minVertex] = true; //위의 반복문을 통해 도출된 가장 가까운 꼭지점에 방문 표시
                for (int j = 0; j < n; j++) {
                    //방문하지 않았고 minVertex와의 가중치가 존재하는(minVertex에서 연결된) 꼭지점이라면
                    if (!visited[j] && weight[minVertex][j] != 0) {
                        //지금 그 꼭지점이 가지고 있는 거리값이 minVertex와 가중치를 더한 값보다 크다면 최단거리 갱신
                        if (distance[j] > distance[minVertex] + weight[minVertex][j]) {
                            distance[j] = distance[minVertex] + weight[minVertex][j];
                            saveRoute[j] = vertex[minVertex]; //최단거리가 갱신된 꼭지점의 경로에 minVertex에 해당하는 꼭지점 저장
                        }
                    }
                }
            }
            System.out.println("시작 꼭지점 " + vertex[a] + "부터 꼭지점 " + vertex[b] + "까지의 거리 : " + distance[b]);
            return distance[b];
        }
        public String result_route(int a, int b) {
            //시작 꼭지점부터 특정 꼭지점까지의 경로 출력
            String rout = null;
            for (int i = 0; i < n; i++) {
                String route = "";
                int index = i;
                while (true) {
                    if (saveRoute[index] == vertex[index]) break; //시작 꼭지점일 경우 break
                    route += " " + saveRoute[index];
                    index = stringToInt(saveRoute[index]); //결정적인 역할을 한 꼭지점을 int형으로 바꿔서 index에 저장
                }
                StringBuilder sb = new StringBuilder(route);
                // 만약 꼭짓점이 도착지점이라면 저장
                if (i == b) {
                    rout = (sb.reverse() + vertex[i]);
                }
            }
            System.out.println("시작 꼭지점 " + vertex[a] + "부터 꼭지점 " + vertex[b] + "까지의 경로 : " + rout);
            return rout;
        }
    }

    public String stringConversion(String a){
        a = a.replace(" ","-");
        a = a.replace("c-o","성결관 옆 윗길");
        a = a.replace("o-c","성결관 옆 아랫길");
        a = a.replace("e-d","e");
        a = a.replace("d-e","e");
        a = a.replace("f-j","f-학생회관 엘레베이터-j");
        a = a.replace("j-f","j-학생회관 엘레베이터-f");
        a = a.replace("q-m","q-재림관 뒷길");
        a = a.replace("m-q","재림관 뒷길-q");
        a = a.replace("a-b","a-올라가는 길");
        a = a.replace("b-a","내려가는 길-a");
        a = a.replace("c-j","성결관 뒷길-j");
        a = a.replace("j-c","j-성결관 뒷길");
        a = a.replace("q-m","학술정보관-재림관 뒷길");
        a = a.replace("m-q","재림관 뒷길-학술정보관");
        a = a.replace("a","정문");
        a = a.replace("b","유치원 옆");
        a = a.replace("c","유치원 앞 삼거리");
        a = a.replace("d","성결관 엘레베이터 앞");
        a = a.replace("e","성결관");
        a = a.replace("f","학생회관");
        a = a.replace("g","학생회관 4층 뒷문");
        a = a.replace("h","영암관 옆");
        a = a.replace("i","영암관");
        a = a.replace("j","중생관");
        a = a.replace("k","중생관 옆문");
        a = a.replace("l","기념관 옆문");
        a = a.replace("n","기념관");
        a = a.replace("m","재림관 옆 계단");
        a = a.replace("o","주차장 사이 계단");
        a = a.replace("p","재림관");
        a = a.replace("q","학술정보관");
        System.out.println(a);
        return a;
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
}
