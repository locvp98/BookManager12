package com.example.dell.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bookmanager.account.ThemtaikhoaiActivity;
import com.example.dell.bookmanager.adapter.CustumNguoiDung;
import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.Nguoidung;

import java.util.List;


public class DangKiActivity extends AppCompatActivity {

    private ImageView imgquaylai;
    private EditText edtusenamedk;
    private EditText edtpassworddk;
    private Button btnlogindk;
    private TextView tvlogindk;
    private DBManager dbManager;
    private List<Nguoidung> nguoidungList;
    private CustumNguoiDung custumadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        anhxadangki();
        dbManager=new DBManager(this);
        nguoidungList=dbManager.getallnguoidung();


        imgquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnlogindk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usenamedk=edtusenamedk.getText().toString().trim();
                String passworđk=edtpassworddk.getText().toString().trim();

                if (usenamedk.equals("")){
                    edtusenamedk.setError(getString(R.string.loitkdk));
                }
                if(passworđk.equals("")){
                    edtpassworddk.setError(getString(R.string.loidkmk));
                }

               else {
                    String tendangnhap = edtusenamedk.getText().toString();
                    String matkhau = edtpassworddk.getText().toString();
                    Nguoidung nguoidung=new Nguoidung(tendangnhap,matkhau);
                    if (nguoidung != null) {
                        dbManager.themnguoidung(nguoidung);
                        edtusenamedk.setText("");
                        edtpassworddk.setText("");
                        Toast.makeText(DangKiActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        tvlogindk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    private void anhxadangki() {
        imgquaylai = (ImageView) findViewById(R.id.imgquaylai);
        edtusenamedk = (EditText) findViewById(R.id.edtusenamedk);
        edtpassworddk = (EditText) findViewById(R.id.edtpassworddk);
        btnlogindk = (Button) findViewById(R.id.btnlogindk);
        tvlogindk = (TextView) findViewById(R.id.tvlogindk);
    }

}
