package com.example.dell.bookmanager.catalog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.bookmanager.R;
import com.example.dell.bookmanager.adapter.CustumTheLoai;
import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.Nguoidung;
import com.example.dell.bookmanager.model.TheLoai;
import com.example.dell.bookmanager.sqliteDAO.TheLoaiDAO;

import java.util.ArrayList;
import java.util.List;

public class TheloaiActivity extends AppCompatActivity {
    private ImageView imgthem;
    private DBManager dbManager;
    private ListView lvtheloai;
    private List<TheLoai> theLoaiList;
    private TheLoaiDAO theLoaiDAO;
    private  CustumTheLoai custumTheLoai;
    private SearchView svtimkiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloai);

        Toast.makeText(TheloaiActivity.this,"chạm lâu để thay đổi thông tin",Toast.LENGTH_LONG).show();

        dbManager=new DBManager(this);
        theLoaiDAO=new TheLoaiDAO(dbManager);
        theLoaiList=theLoaiDAO.getalltheloai();

        imgthem = (ImageView) findViewById(R.id.imgthem);
        lvtheloai=findViewById(R.id.lvtheloai);
        svtimkiem=findViewById(R.id.svtimkiem);
        setadapter();


        svtimkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String ma) {

                if(theLoaiList.contains(ma)){
                    custumTheLoai.getFilter().filter(ma);
                }else{
                    Toast.makeText(TheloaiActivity.this, "khong co ma",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

      //  custumTheLoai= (CustumTheLoai) new ArrayAdapter<TheLoai>(TheloaiActivity.this,R.layout.custumtheloai,theLoaiList);


        imgthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(TheloaiActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_themsach);

                final EditText edtma = (EditText) dialog.findViewById(R.id.edtma);
                final EditText edtten = (EditText) dialog.findViewById(R.id.edtten);
                final EditText edtvitri = (EditText) dialog.findViewById(R.id.edtvitri);
                final EditText edtmota = (EditText) dialog.findViewById(R.id.edtmota);
                Button btnluu = (Button) dialog.findViewById(R.id.btnluu);
                Button btnthoat = (Button) dialog.findViewById(R.id.btnthoat);

                btnluu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       String ma=edtma.getText().toString();
                       String ten=edtten.getText().toString();
                       String vitri=edtvitri.getText().toString();
                       String mota=edtmota.getText().toString();
                       TheLoai theLoai=new TheLoai(ma,ten,vitri,mota);

//                       if (theLoaiDAO.getUserBytheloai(ma)){
//                           Toast.makeText(TheloaiActivity.this,"Mã trùng ",Toast.LENGTH_LONG).show();
//                       }

                     if (theLoai!=null){
                           theLoaiDAO.themtheloai(theLoai);

                           setadapter();
                           updatelistthetloai();

                           dialog.cancel();
                       }
                    }
                });

                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });


        lvtheloai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog dialog=new Dialog(TheloaiActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.update_theloai);

                TheLoai theLoai1=theLoaiList.get(position);

                final EditText  edtid = (EditText) dialog.findViewById(R.id.edtid);
                edtid.setVisibility(View.GONE);
                final EditText edtma = (EditText) dialog.findViewById(R.id.edtma);
                edtma.setVisibility(View.GONE);
                final EditText edtten = (EditText) dialog.findViewById(R.id.edtten);
                final EditText edtvitri = (EditText) dialog.findViewById(R.id.edtvitri);
                final EditText edtmota = (EditText) dialog.findViewById(R.id.edtmota);
                Button btnluu = (Button) dialog.findViewById(R.id.btnluu);
                Button btnthoat = (Button) dialog.findViewById(R.id.btnthoat);


                edtid.setText(String.valueOf(theLoai1.getMid()));
                edtma.setText(theLoai1.getmMa());
                edtten.setText(theLoai1.getmTentheloai());
                edtvitri.setText(theLoai1.getmVitri());
                edtmota.setText(theLoai1.getmMota());


                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnluu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TheLoai theLoai=new TheLoai();
                        theLoai.setMid(Integer.parseInt(String.valueOf(edtid.getText())));
                        theLoai.setmMa(edtma.getText()+"");
                        theLoai.setmTentheloai(edtten.getText()+"");
                        theLoai.setmVitri(edtvitri.getText()+"");
                        theLoai.setmMota(edtmota.getText()+"");

                        int kiemtra=theLoaiDAO.updatetheloai(theLoai);
                        if (kiemtra>0){
                            updatelistthetloai();

                        }
                        dialog.cancel();
                    }
                });
                dialog.show();


                return false;
            }
        });
    }

    private void setadapter() {

        if (custumTheLoai==null){
            custumTheLoai=new CustumTheLoai(this,R.layout.custumtheloai,theLoaiList);
            lvtheloai.setAdapter(custumTheLoai);
        }
        else {
            custumTheLoai.notifyDataSetChanged();
            lvtheloai.setSelection(custumTheLoai.getCount()-1);

        }
    }

    public void xoatheloai(int position) {
        TheLoai theLoai = theLoaiList.get(position);
        int kiemtra = theLoaiDAO.deletetheloai(theLoai.getMid());
        if (kiemtra > 0) {
            updatelistthetloai();
       }

    }
    public void updatelistthetloai(){
        theLoaiList.clear();
        theLoaiList.addAll(theLoaiDAO.getalltheloai());
        if (theLoaiList!=null){
            custumTheLoai.notifyDataSetChanged();
        }
    }
}
