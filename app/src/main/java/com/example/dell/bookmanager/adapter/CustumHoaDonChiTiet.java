package com.example.dell.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.bookmanager.R;
import com.example.dell.bookmanager.model.HoaDonChiTiet;

import java.util.List;

public class CustumHoaDonChiTiet extends ArrayAdapter<HoaDonChiTiet> {
    private Context context;
    private int resource;
    List<HoaDonChiTiet> hoaDonChiTietList;


    public CustumHoaDonChiTiet(@NonNull Context context, int resource, @NonNull List<HoaDonChiTiet> objects) {
        super(context, resource, objects);

        this.context=context;
        this.resource=resource;
        this.hoaDonChiTietList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_hdct,parent,false);

            viewHolder.tvmahd = convertView.findViewById(R.id.tvmahd);
            viewHolder.tvsoluong = convertView.findViewById(R.id.tvsoluong);
            viewHolder.tvgiabia = convertView.findViewById(R.id.tvgiabia);
            viewHolder.tvthanhtien = convertView.findViewById(R.id.tvthanhtien);
            viewHolder.imaxoa = convertView.findViewById(R.id.imaxoa);
            convertView.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        HoaDonChiTiet hoaDonChiTiet=hoaDonChiTietList.get(position);

        //tiep

        return super.getView(position, convertView, parent);
    }

    public class ViewHolder{
        private TextView tvmahd;
        private TextView tvsoluong;
        private TextView tvgiabia;
        private TextView tvthanhtien;
        private ImageView imaxoa;


    }
}
