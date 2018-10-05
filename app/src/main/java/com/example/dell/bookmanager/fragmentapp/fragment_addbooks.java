package com.example.dell.bookmanager.fragmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bookmanager.catalog.SachBanAct;
import com.example.dell.bookmanager.R;
import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.Sachban;
import com.example.dell.bookmanager.sqliteDAO.SachDAO;

public class fragment_addbooks extends Fragment {
    private EditText edtmasach;
    private EditText lltensach;
    private EditText edttacgia;
    private EditText edtgiasach;
    private EditText edtsoluong;
    private TextView tvlich;
    private Button btnluu;
    private Button btnhienthi;
    private EditText edtthongbao;
    private LinearLayout llchonlich;
    private ImageView xoatrang;
    private DBManager dbManager;
    private SachDAO sachDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View view=inflater.inflate(R.layout.fragment_books,container,false);

     khoitao(view);


      sachDAO=new SachDAO(new DBManager(getActivity()));

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masach=edtmasach.getText().toString();
                String tensach=lltensach.getText().toString();
                String tacgia=edttacgia.getText().toString();
                String soluong=edtsoluong.getText().toString();

                if (masach.equals("") && tensach.equals("") &&tacgia.equals("") && soluong.equals("")){
                    edtthongbao.setError(getString(R.string.khongduocdetrong));
                }

                else {
                   Sachban sachban=creatsach();
                   if (sachban != null){
                       sachDAO.themsach(sachban);
                   }
                    String thongbao=getString(R.string.xuathoadon);
                    Toast.makeText(getActivity(),""+thongbao,Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnhienthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SachBanAct.class));
            }
        });
        xoatrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtmasach.setText("");
                edttacgia.setText("");
                edtsoluong.setText("");
                lltensach.setText("");
                edtgiasach.setText("");

            }
        });

        return view;
    }

    private void khoitao(View view) {
        edtmasach = (EditText) view.findViewById(R.id.edtmasach);
        lltensach = view.findViewById(R.id.edttensach);
        edttacgia = (EditText) view.findViewById(R.id.edttacgia);
        edtgiasach = (EditText) view.findViewById(R.id.edtgiasach);
        edtsoluong = (EditText) view.findViewById(R.id.edtsoluong);
        tvlich = (TextView) view.findViewById(R.id.tvlich);
        btnluu = (Button) view.findViewById(R.id.btnluu);
        btnhienthi = (Button) view.findViewById(R.id.btnhienthi);
        edtthongbao=view.findViewById(R.id.edtthongbao);
        xoatrang=view.findViewById(R.id.xoatrang);
    }

    private Sachban creatsach() {

        String ma=edtmasach.getText().toString();
        String ten=lltensach.getText().toString();
        String tacgia=edttacgia.getText().toString();
        String gia=edtgiasach.getText().toString();
        String soluong=edtsoluong.getText().toString();
       Sachban sachban=new Sachban(ma,ten,tacgia,gia,soluong);

        return sachban;

    }
}
