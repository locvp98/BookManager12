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
import com.example.dell.bookmanager.adapter.CustumSach;
import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.Sachban;
import com.example.dell.bookmanager.sqliteDAO.SachDAO;

import java.util.List;

public class ActSach extends AppCompatActivity {
   private ListView lvsachbans;
    private DBManager dbManager;
    private  SachDAO sachDAO;
    private  CustumSach custumSach;
    private  List<Sachban> sachbanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sachban);

       lvsachbans=findViewById(R.id.lvsachban);
        sachDAO=new SachDAO(new DBManager(this));
        sachbanList=sachDAO.getallsachban();
        setadapter();

        lvsachbans.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog dialog=new Dialog(ActSach.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogsach);
                Sachban sachban=sachbanList.get(position);


                final EditText   edtid = dialog.findViewById(R.id.edtid);
                edtid.setVisibility(View.GONE);
                final EditText   edtmasach2 = dialog.findViewById(R.id.edtmasach2);
                edtmasach2.setVisibility(View.GONE);
                final EditText   edttensach1 = dialog.findViewById(R.id.edttensach1);
                EditText   edttacgia1 = dialog.findViewById(R.id.edttacgia1);
                final EditText   edtgia = dialog.findViewById(R.id.edtgia);
                final EditText   edtsoluong = dialog.findViewById(R.id.edtsoluong);
                Button  btnok = dialog.findViewById(R.id.btnok);
                Button  btnhuy = dialog.findViewById(R.id.btnhuy);

                edtid.setText(String.valueOf(sachban.getMid()));
                edtmasach2.setText(sachban.getMma());
                edttensach1.setText(sachban.getMten());
                edttacgia1.setText(sachban.getMtacgia());
                edtgia.setText(sachban.getMgia());
                edtsoluong.setText(sachban.getMsoluong());

                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Sachban sachban1=new Sachban();
                        sachban1.setMid(Integer.parseInt(String.valueOf(edtid.getText())));
                        sachban1.setMma(edtmasach2.getText()+"");
                        sachban1.setMten(edttensach1.getText()+"");
                        sachban1.setMtacgia(edtgia.getText()+"");
                        sachban1.setMgia(edtgia.getText()+"");
                        sachban1.setMsoluong(edtsoluong.getText()+"");

                        int kiemtra=sachDAO.updatesach(sachban1);
                        if (kiemtra>0){
                            updatesachlist();

                        }
                        dialog.cancel();
                    }
                });


                dialog.show();

                return false;
            }
        });


    }

    private void setadapter(){
        if (custumSach==null) {
            custumSach=new CustumSach(ActSach.this,R.layout.item_sach,sachbanList);
            lvsachbans.setAdapter(custumSach);
        }
        else {
            custumSach.notifyDataSetChanged();
        }
    }

    public  void delete(int position){
        Sachban sachban=sachbanList.get(position);
        int kiemtra = sachDAO.deletesach(sachban.getMid());
        if(kiemtra>0) {
            updatesachlist();
        }
    }

    public void updatesachlist(){
        sachbanList.clear();
        sachbanList.addAll(sachDAO.getallsachban());
        if (sachbanList!=null){
            custumSach.notifyDataSetChanged();
        }
    }
}
