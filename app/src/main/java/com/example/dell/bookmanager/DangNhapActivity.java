package com.example.dell.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.giaodienchinh.ManChinhActivity;
import com.example.dell.bookmanager.model.Nguoidung;

import java.util.List;


public class DangNhapActivity extends AppCompatActivity {

    private EditText edtusename;
    private EditText edtpassword;
    private Button btnlogin;
    private TextView forgotpassword;
    private TextView register;
    private DBManager dbManager;
    private List<Nguoidung> nguoidungList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

              xulyanhxa();

              dbManager=new DBManager(this);
              nguoidungList=dbManager.getallnguoidung();
        Nguoidung user= new Nguoidung("admin", "123456", "nguyenvanloc", "0123");

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usename=edtusename.getText().toString().trim();
                String password=edtpassword.getText().toString().trim();

                if (usename.equals("")){
                    edtusename.setError(getString(R.string.noi));
                }
                if (password.equals("")){
                    edtpassword.setError(getString(R.string.loi));
                }
                else {
                    Nguoidung nguoidung = dbManager.getUserByUsername(usename);

                    if (nguoidung !=null){
                        String passwordOnDatabase = nguoidung.getMpassword();

                        if (passwordOnDatabase.equals(password)){

                            edtusename.setText("");
                            edtpassword.setText("");
                            Intent intent = new Intent(DangNhapActivity.this, ManChinhActivity.class);
                            startActivity(intent);
                        }

                        else Toast.makeText(DangNhapActivity.this,getString(R.string.loi),Toast.LENGTH_SHORT).show();
                    }
                    else  Toast.makeText(DangNhapActivity.this,getString(R.string.loi),Toast.LENGTH_SHORT).show();

                }

                }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this,DangKiActivity.class));
            }
        });

    }

    private void xulyanhxa() {
        edtusename = (EditText) findViewById(R.id.edtusename);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        register = (TextView) findViewById(R.id.register);
    }


}
