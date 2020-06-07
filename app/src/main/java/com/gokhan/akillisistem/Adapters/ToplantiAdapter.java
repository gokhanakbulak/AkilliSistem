package com.gokhan.akillisistem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Toplanti;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class ToplantiAdapter  extends BaseAdapter {
    Context context;
    ArrayList<Toplanti> arrayList;
    Database db;

    public ToplantiAdapter(Context context, ArrayList<Toplanti> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mytoplantilist, null);
            TextView t1_tName = convertView.findViewById(R.id.toplanti_ismi);
            TextView t1_tDate = convertView.findViewById(R.id.toplanti_tarihi);

            Toplanti toplanti = arrayList.get(position);
            t1_tName.setText(toplanti.gettName());
            t1_tDate.setText(toplanti.gettDate());


        }
        return convertView;
    }
}