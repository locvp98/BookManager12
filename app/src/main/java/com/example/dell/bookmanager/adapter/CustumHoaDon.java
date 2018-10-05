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
import com.example.dell.bookmanager.catalog.HoaDonActivity;
import com.example.dell.bookmanager.model.HoaDon;

import java.util.List;

public class CustumHoaDon extends ArrayAdapter<HoaDon> {
    private HoaDonActivity context;
    private int resource;
    private List<HoaDon> hoaDonList;


    public CustumHoaDon(@NonNull HoaDonActivity context, int resource, @NonNull List<HoaDon> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.hoaDonList=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_hoadon,parent,false);
            viewHolder=new ViewHolder();

            viewHolder.tvma = (TextView) convertView.findViewById(R.id.tvma);
            viewHolder.tvngay = (TextView) convertView.findViewById(R.id.tvngay);
            viewHolder.tvxoa = (ImageView) convertView.findViewById(R.id.tvxoa);

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        HoaDon hoaDon=hoaDonList.get(position);
        viewHolder.tvma.setText(hoaDon.getMma());
        viewHolder.tvngay.setText(hoaDon.getMngaythang());

        viewHolder.tvxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(position);
            }
        });


        return convertView;
    }

    public class ViewHolder{

        private TextView tvma;
        private TextView tvngay;
        private ImageView tvxoa;

    }
}
