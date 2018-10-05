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
import com.example.dell.bookmanager.catalog.ActSach;
import com.example.dell.bookmanager.model.Sachban;

import java.util.List;

public class CustumSach extends ArrayAdapter<Sachban>{
    private ActSach context;
    private int resource;
    private List<Sachban> sachbanList;

    public CustumSach(@NonNull ActSach context, int resource, @NonNull List<Sachban> sachbanList) {
        super(context, resource, sachbanList);

        this.context=context;
        this.resource=resource;
        this.sachbanList=sachbanList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       ViewHolder viewHolder;
       if (convertView==null){
           convertView=LayoutInflater.from(context).inflate(R.layout.item_sach,parent,false);
           viewHolder=new ViewHolder();
           viewHolder.tvma = convertView.findViewById(R.id.tvma);
           viewHolder.tvtensach = convertView.findViewById(R.id.tvtensach);
           viewHolder.tvgia = convertView.findViewById(R.id.tvgia);
           viewHolder.xoa = convertView.findViewById(R.id.xoa);
           convertView.setTag(viewHolder);
       }
       else {
           viewHolder= (ViewHolder) convertView.getTag();
       }
       Sachban sachban=sachbanList.get(position);

       viewHolder.tvma.setText(sachban.getMma());
       viewHolder.tvtensach.setText(sachban.getMten());
       viewHolder.tvgia.setText(sachban.getMgia());

       viewHolder.xoa.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               context.delete(position);
           }
       });

        return convertView;
    }
    public  class  ViewHolder{

        private TextView tvma;
        private TextView tvtensach;
        private TextView tvgia;
        private ImageView xoa;

    }
}
