package com.example.dell.bookmanager.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dell.bookmanager.R;

public class NgonNguActivity extends AppCompatActivity {
    private ImageView imgquaylai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngon_ngu);
        imgquaylai=findViewById(R.id.imgquaylai);

        imgquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
