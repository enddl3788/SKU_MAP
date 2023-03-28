package com.yebin.SKUMAP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CafeteriaActivity extends AppCompatActivity {

    private ImageButton btn_cafeteria_student, btn_cafeteria_staff;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_option); //xml , java 소스 연결

        btn_cafeteria_student = findViewById(R.id.btn_cafeteria_student);
        btn_cafeteria_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudentCafeteriaActivity.class);
                startActivity(intent);
            }
        });

        btn_cafeteria_staff = findViewById(R.id.btn_cafeteria_staff);
        btn_cafeteria_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StaffCafeteriaActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //다음 화면에 실행할 애니메이션, 현재 화면에 실행할 애니메이션
        finish();
    }
}
