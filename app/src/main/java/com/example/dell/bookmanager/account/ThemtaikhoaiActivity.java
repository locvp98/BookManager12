package com.example.dell.bookmanager.account;

import android.app.Dialog;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.bookmanager.R;
import com.example.dell.bookmanager.adapter.CustumNguoiDung;
import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.HoaDon;
import com.example.dell.bookmanager.model.Nguoidung;


import java.util.ArrayList;
import java.util.List;

public class ThemtaikhoaiActivity extends AppCompatActivity {
    private ImageView imgtrove;
    private ListView lvthemnguoidung;
    private FloatingActionButton flthem;
    private DBManager dbManager;
    private List<Nguoidung> nguoidungList;
    private CustumNguoiDung custumadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themtaikhoai);
        imgtrove = (ImageView) findViewById(R.id.imgtrove);
        lvthemnguoidung = (ListView) findViewById(R.id.lvthemnguoidung);
        flthem = (FloatingActionButton) findViewById(R.id.flthem);

        dbManager=new DBManager(this);
        nguoidungList=dbManager.getallnguoidung();
        setAdapter();

        imgtrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        flthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ThemtaikhoaiActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogthemtaikhoan);

                final EditText edtnhaclai = (EditText) dialog.findViewById(R.id.edtnhaclai);
                final EditText edtUsenamet = (EditText) dialog.findViewById(R.id.edtUsenamet);
                final EditText edtPasswordt = (EditText) dialog.findViewById(R.id.edtPasswordt);
                final EditText edtsodienthoai = (EditText) dialog.findViewById(R.id.edtsodienthoai);
                final EditText edthovaten = (EditText) dialog.findViewById(R.id.edthovaten);
                Button btnluutk = (Button) dialog.findViewById(R.id.btnluutk);
                Button btnthoat = (Button) dialog.findViewById(R.id.btnthoat);

                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnluutk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //sử lý sự kiện thê

                            String tendangnhap = edtUsenamet.getText().toString();
                            String matkhau = edtPasswordt.getText().toString();
                            String sodienthoai = edtsodienthoai.getText().toString();
                            String hovaten = edthovaten.getText().toString();
                            Nguoidung nguoidung = new Nguoidung(tendangnhap, matkhau, sodienthoai, hovaten);

                            if (nguoidung != null) {
                            dbManager.themnguoidung(nguoidung);

                        }
                        updateListNguoidung();
                       setAdapter();
                        dialog.cancel();
                    }

                }
                );

                dialog.show();
            }
        });


        lvthemnguoidung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog dialog=new Dialog(ThemtaikhoaiActivity.this);

                Nguoidung nguoidung=nguoidungList.get(position);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogupdate);

                final EditText edtid = (EditText) dialog.findViewById(R.id.edtid);
                edtid.setVisibility(view.GONE);
                final EditText edtUsenamet = (EditText) dialog.findViewById(R.id.edtUsenamet);
                final EditText edtPasswordt = (EditText) dialog.findViewById(R.id.edtPasswordt);
                final EditText edtsodienthoai = (EditText) dialog.findViewById(R.id.edtsodienthoai);
                final EditText edthovaten = (EditText) dialog.findViewById(R.id.edthovaten);
                Button btnluutk = (Button) dialog.findViewById(R.id.btnluutk);
                Button btnthoat = (Button) dialog.findViewById(R.id.btnthoat);

                edtid.setText(String.valueOf(nguoidung.getMid()));
                edtUsenamet.setText(nguoidung.getMname());
                edtPasswordt.setText(nguoidung.getMpassword());
                edtsodienthoai.setText(nguoidung.getMphone());
                edthovaten.setText(nguoidung.getMhoten());

                btnluutk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Nguoidung nguoidung1 = new Nguoidung();
                       nguoidung1.setMid(Integer.parseInt(String.valueOf(edtid.getText())));
                        nguoidung1.setMname(edtUsenamet.getText()+"");
                        nguoidung1.setMpassword(edtPasswordt.getText()+"");
                        nguoidung1.setMphone(edtsodienthoai.getText()+"");
                        nguoidung1.setMhoten(edthovaten.getText()+"");
                        int kiemtra = dbManager.updateNguoidung(nguoidung1);
                        if(kiemtra>0){
                            updateListNguoidung();
                        }

                        dialog.cancel();
                    }
                });

                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();


                return false;
            }
        });
    }

    public void setAdapter() {

        if (custumadapter == null) {
            custumadapter = new CustumNguoiDung(this, R.layout.layoutcustomlisthemnguoidung, nguoidungList);
            lvthemnguoidung.setAdapter(custumadapter);
        } else {
            custumadapter.notifyDataSetChanged();
            lvthemnguoidung.setSelection(custumadapter.getCount() - 1);
        }
    }

    public void xoanguoidung(int position){
        Nguoidung student = nguoidungList.get(position);
        int kiemtra = dbManager.deleteStudent(student.getMid());
        if(kiemtra>0) {
            updateListNguoidung();
        }
    }
    public void updateListNguoidung() {
        nguoidungList.clear();
        nguoidungList.addAll(dbManager.getallnguoidung());
        if (custumadapter != null) {
            custumadapter.notifyDataSetChanged();
        }
    }





}
