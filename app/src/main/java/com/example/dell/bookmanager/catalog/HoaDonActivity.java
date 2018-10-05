package com.example.dell.bookmanager.catalog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dell.bookmanager.R;
import com.example.dell.bookmanager.adapter.CustumHoaDon;
import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.fragmentapp.fragment_bill;
import com.example.dell.bookmanager.model.HoaDon;
import com.example.dell.bookmanager.model.Nguoidung;
import com.example.dell.bookmanager.model.TheLoai;
import com.example.dell.bookmanager.sqliteDAO.HoaDonDAO;

import java.util.List;

public class HoaDonActivity extends AppCompatActivity {
    private ListView lvhoadon;
    private fragment_bill context;
    private CustumHoaDon custumAdapter;
    private List<HoaDon> hoaDonList;
    private HoaDonDAO hoaDonDAO;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        lvhoadon = (ListView) findViewById(R.id.lvhoadon);
        dbManager=new DBManager(this);
        hoaDonDAO=new HoaDonDAO(dbManager);
        hoaDonList=hoaDonDAO.getallhoadon();
        setAdapter();


        lvhoadon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog dialog=new Dialog(HoaDonActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.update_theloai);

                HoaDon hoaDon=hoaDonList.get(position);

                final EditText edtid = (EditText) dialog.findViewById(R.id.edtid);
                edtid.setVisibility(View.GONE);
                final EditText edtma = (EditText) dialog.findViewById(R.id.edtma);
                edtma.setVisibility(View.GONE);
                final EditText vitr = (EditText) dialog.findViewById(R.id.edtvitri);
                final EditText ten = (EditText) dialog.findViewById(R.id.edtten);
                vitr.setVisibility(View.GONE);
                ten.setVisibility(View.GONE);
                final EditText edtmota = (EditText) dialog.findViewById(R.id.edtmota);
                Button btnluu = (Button) dialog.findViewById(R.id.btnluu);
                Button btnthoat = (Button) dialog.findViewById(R.id.btnthoat);


                edtid.setText(String.valueOf(hoaDon.getMid()));
                edtma.setText(hoaDon.getMma());
                edtmota.setText(hoaDon.getMngaythang());


                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnluu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HoaDon hoaDon=new HoaDon();
                        hoaDon.setMid(Integer.parseInt(String.valueOf(edtid.getText())));
                        hoaDon.setMma(edtma.getText()+"");
                        hoaDon.setMngaythang(edtmota.getText()+"");

                        int kiemtra=hoaDonDAO.updatehoadeon(hoaDon);
                        if (kiemtra>0){
                            updatehoadon();

                        }
                        dialog.cancel();
                    }
                });
                dialog.show();


                return false;
            }
        });


    }

    public void setAdapter() {
        if (custumAdapter == null) {
            custumAdapter = new CustumHoaDon(this, R.layout.item_hoadon, hoaDonList);
            lvhoadon.setAdapter(custumAdapter);
        } else {
            custumAdapter.notifyDataSetChanged();
            lvhoadon.setSelection(custumAdapter.getCount() - 1);
        }
    }
    public  void delete(int position){
      HoaDon hoaDon=hoaDonList.get(position);
        int kiemtra = hoaDonDAO.deletehodon(hoaDon.getMid());
        if(kiemtra>0) {
            updatehoadon();
        }
    }
    public void updatehoadon(){
        hoaDonList.clear();
        hoaDonList.addAll(hoaDonDAO.getallhoadon());
        if (custumAdapter != null) {
            custumAdapter.notifyDataSetChanged();
        }
    }
}
