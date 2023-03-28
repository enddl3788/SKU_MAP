package com.yebin.SKUMAP;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoticeActivity extends Activity {
    private ImageView iv_notice;
    private TextView tv_notice;
    private int x = 0;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String uri;
    private String Tag = "NoticeActivity";

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notice); //xml , java 소스 연결

        tv_notice = findViewById(R.id.tv_notice);
        iv_notice = findViewById(R.id.iv_notice);
        readFirebase("EVENT", "EVENT_1");
        iv_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }
    public void sendImageRequest(String url) {
        ImageLoadTask task = new ImageLoadTask(url,iv_notice);
        task.execute();
    }

    public void wordSearchLink(String word) {
        Pattern pattern = Pattern.compile("[&](.*?)[&]");
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) {  // 일치하는 게 있다면
            uri = matcher.group(1);
            Log.d(Tag, matcher.group(1));
        }
        else {
            Log.d(Tag, "error");
            finish();
        }
    }

    public void wordSearchImage(String word) {
        Pattern pattern = Pattern.compile("(\\bimage_url=\\b)(.*?)(\\b, link=&\\b)");
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) {  // 일치하는 게 있다면
            sendImageRequest(matcher.group(2));
            Log.d(Tag, matcher.group(2));
        }
        else {
            Log.d(Tag, "error");
            finish();
        }
    }
    public void wordSearchText(String word) {
        Pattern pattern = Pattern.compile("[|](.*?)[|]");
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) {  // 일치하는 게 있다면
            String notice = matcher.group(1);
            notice = notice.replace("\\n","\n");
            tv_notice.setText(notice);
            Log.d(Tag, matcher.group(1));
        } else {
            Log.d(Tag, "error");
            finish();
        }
    }

    public void readFirebase(String collection, String document) {
        DocumentReference docRef = db.collection(collection).document(document);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(Tag, "DocumentSnapshot data: " + document.getData());
                        wordSearchImage(document.getData().toString());
                        wordSearchText(document.getData().toString());
                        wordSearchLink(document.getData().toString());
                    } else {
                        Log.d(Tag, "No such document");
                    }
                } else {
                    Log.d(Tag, "get failed with ", task.getException());
                }
            }
        });
    }
}