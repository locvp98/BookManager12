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
import com.example.dell.bookmanager.catalog.TheloaiActivity;
import com.example.dell.bookmanager.model.TheLoai;

import java.util.List;

public class CustumTheLoai extends ArrayAdapter<TheLoai> {
    private TheloaiActivity context;
    private int resource;
    private List<TheLoai> theLoaiList;

    public CustumTheLoai(@NonNull TheloaiActivity context, int resource, @NonNull List<TheLoai> objects) {
        super(context, resource, objects);

        this.context=context;
        this.resource=resource;
        this.theLoaiList=objects;


    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView==null){
           convertView=LayoutInflater.from(context).inflate(R.layout.custumtheloai,parent,false);
           viewHolder=new ViewHolder();

            viewHolder.tvma = (TextView) convertView.findViewById(R.id.tvma);
            viewHolder.tvten = (TextView) convertView.findViewById(R.id.tvten);
            viewHolder.tvvitri = (TextView) convertView.findViewById(R.id.tvvitri);
            viewHolder.imgxoa = (ImageView) convertView.findViewById(R.id.imgxoa);

           convertView.setTag(viewHolder);

        }else {
           viewHolder= (ViewHolder) convertView.getTag();
        }

        TheLoai theLoai=theLoaiList.get(position);

        viewHolder.tvma.setText(theLoai.getmMa());
        viewHolder.tvten.setText(theLoai.getmTentheloai());
        viewHolder.tvvitri.setText(theLoai.getmVitri());


        viewHolder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.xoatheloai(position);
            }
        });

        return convertView;
    }

    public class ViewHolder{
        private TextView tvma;
        private TextView tvten;
        private TextView tvvitri;
        private ImageView imgxoa;

    }
}
