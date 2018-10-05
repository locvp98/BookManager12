package com.example.dell.bookmanager.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dell.bookmanager.R;
import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.HoaDon;
import com.example.dell.bookmanager.model.HoaDonChiTiet;
import com.example.dell.bookmanager.model.Sachban;
import com.example.dell.bookmanager.sqliteDAO.HDCTDAO;
import com.example.dell.bookmanager.sqliteDAO.SachDAO;

import java.sql.Date;
import java.util.List;

public class HoaDonChiTietACT extends AppCompatActivity {
    private EditText edtma;
    private EditText edtmasach1;
    private EditText edtsoluong;
    private EditText edtgia;
    private EditText rdtthanhtien;
    private Button them;
    private Button thanhtoan;
    private ListView lvhoadonchitiet;
    private List<HoaDonChiTiet> hoaDonChiTietList;
    private HDCTDAO hdctdao;
    double thanhtien = 0;
    SachDAO sachDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet_act);

        edtma = findViewById(R.id.edtma);
        edtmasach1 = findViewById(R.id.edtmasach1);
        edtsoluong = findViewById(R.id.edtsoluong);
        edtgia = findViewById(R.id.edtgia);
        edtgia.setVisibility(View.GONE);
        rdtthanhtien = findViewById(R.id.rdtthanhtien);
        rdtthanhtien.setVisibility(View.GONE);
        them = findViewById(R.id.them);
        thanhtoan = findViewById(R.id.thanhtoan);
        lvhoadonchitiet = findViewById(R.id.lvhoadonchitiet);

        hdctdao = new HDCTDAO(new DBManager(this));
        hoaDonChiTietList = hdctdao.getallhdct();

        String ma = edtma.getText().toString();
        String masach = edtmasach1.getText().toString();
        String sl = edtsoluong.getText().toString();
        String gia = edtsoluong.getText().toString();
        String tanhtien = edtsoluong.getText().toString();

        final HoaDonChiTiet hd = new HoaDonChiTiet(ma, masach, gia, sl, tanhtien);


        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhtien = 0;
                hdctdao.themhdct(hd);
                // thanhtien = thanhtien + hd.getMsoluong() * hd.getMma();

            }
        });


    }

}


