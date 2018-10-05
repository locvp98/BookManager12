package com.example.dell.bookmanager.fragmentapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bookmanager.account.HoaDonChiTietACT;
import com.example.dell.bookmanager.adapter.CustumHoaDon;
import com.example.dell.bookmanager.catalog.HoaDonActivity;
import com.example.dell.bookmanager.catalog.TheloaiActivity;
import com.example.dell.bookmanager.R;
import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.HoaDon;
import com.example.dell.bookmanager.sqliteDAO.HoaDonDAO;

import java.util.Calendar;
import java.util.List;

public class fragment_bill extends Fragment {
    private EditText edtmahoadon;
    private LinearLayout lllich;
    private Button btnxacnhan;
    private TextView tvlich;
    private DBManager dbManager;
    private HoaDonDAO hoaDonDAO;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bill,container,false);

        tvlich=view.findViewById(R.id.tvlich);
        edtmahoadon = (EditText) view.findViewById(R.id.edtmahoadon);
        lllich = (LinearLayout) view.findViewById(R.id.lllich);
        btnxacnhan = (Button) view.findViewById(R.id.btnxacnhan);

        final String mahd=edtmahoadon.getText().toString();

        dbManager=new DBManager(getActivity());
        hoaDonDAO = new HoaDonDAO(dbManager);


        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mahoadon=edtmahoadon.getText().toString();

                if (mahoadon.equals("")){
                    edtmahoadon.setError(getString(R.string.chuanhapma));
                }
                else if (!mahoadon.equals("")){

                    HoaDon hoaDon=creathoadon();
                    if (hoaDon!=null){
                        hoaDonDAO.themhoadon(hoaDon);

                        Intent intent=new Intent(getActivity(), HoaDonChiTietACT.class);
                        startActivity(intent);
                    }
                    String thongbao=getString(R.string.xuathoadon);
                    Toast.makeText(getActivity(),""+thongbao,Toast.LENGTH_SHORT).show();
                }
            }
        });

        lllich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                int Year =cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.MONTH)+1;
                int Day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog date=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String stringOfDate = "Ngày "+dayOfMonth + "/" + month + "/" + year;
                        tvlich.setText(tvlich.getText() + "\n " + stringOfDate);
                    }

                }, Year, Month, Day);

                date.show();
            }
        });

        return view;
    }
    private HoaDon creathoadon() {

        String ma=edtmahoadon.getText().toString();
        String ngay=tvlich.getText().toString();
        HoaDon hoaDon=new HoaDon(ma,ngay);
        return hoaDon;

    }
}
