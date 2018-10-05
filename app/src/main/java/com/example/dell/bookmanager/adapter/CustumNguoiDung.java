package com.example.dell.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.bookmanager.R;
import com.example.dell.bookmanager.account.ThemtaikhoaiActivity;
import com.example.dell.bookmanager.model.Nguoidung;

import java.util.List;

public class CustumNguoiDung extends ArrayAdapter<Nguoidung> {
    private ThemtaikhoaiActivity context;
    private int resource;
    private List<Nguoidung> listnguoidung;

    public CustumNguoiDung(@NonNull ThemtaikhoaiActivity context, int resource, @NonNull List<Nguoidung> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listnguoidung = objects;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layoutcustomlisthemnguoidung,parent,false);
            viewHolder=new ViewHolder();

            viewHolder.lltaikhoan = (LinearLayout) convertView.findViewById(R.id.lltaikhoan);
            viewHolder.tvhoten = (TextView) convertView.findViewById(R.id.tvhoten);
            viewHolder.tvsodienthoai = (TextView) convertView.findViewById(R.id.tvsodienthoai);
            viewHolder.imgxoa = (ImageView) convertView.findViewById(R.id.imgxoa);

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Nguoidung nguoidung=listnguoidung.get(position);
        viewHolder.tvhoten.setText(nguoidung.getMhoten());
        viewHolder.tvsodienthoai.setText(nguoidung.getMphone());

        viewHolder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.xoanguoidung(position);
            }
        });

        return convertView;
    }

    public class ViewHolder{
        private LinearLayout lltaikhoan;
        private TextView tvhoten;
        private TextView tvsodienthoai;
        private ImageView imgxoa;


    }
}
