package com.example.dell.bookmanager.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dell.bookmanager.R;

public class DoiTaiKhoanActivity extends AppCompatActivity {

    private ImageView imgtrove;
    private EditText edtusenamethaydoi;
    private EditText edtpasswordthaydoi;
    private Button btnloginthaydoi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_tai_khoan);

        imgtrove = (ImageView) findViewById(R.id.imgtrove);
        edtusenamethaydoi = (EditText) findViewById(R.id.edtusenamethaydoi);
        edtpasswordthaydoi = (EditText) findViewById(R.id.edtpasswordthaydoi);
        btnloginthaydoi = (Button) findViewById(R.id.btnloginthaydoi);

        imgtrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnloginthaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thay đổi thông tin ở đây
            }
        });

    }
}
