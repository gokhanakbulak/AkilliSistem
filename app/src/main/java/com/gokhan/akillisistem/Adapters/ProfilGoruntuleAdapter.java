package com.gokhan.akillisistem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.UserEkip;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class ProfilGoruntuleAdapter extends BaseAdapter {
    Context context;
    ArrayList<UserEkip> arrayList;
    Database db;
    public ProfilGoruntuleAdapter(Context context, ArrayList<UserEkip> arrayList) {
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
            convertView = inflater.inflate(R.layout.myprofil, null);


            TextView t1_id = convertView.findViewById(R.id.g_id);
            TextView t1_fname = convertView.findViewById(R.id.g_name);
            TextView t1_lname = convertView.findViewById(R.id.g_lname);
            TextView t1_email = convertView.findViewById(R.id.g_email);
            TextView tl_ekipn = convertView.findViewById(R.id.g_ekip);
            TextView tl_sifre = convertView.findViewById(R.id.g_sifre);
            Button sil = convertView.findViewById(R.id.sil_btn);

            UserEkip user = arrayList.get(position);
            t1_id.setText(String.valueOf(user.getId()));
            t1_fname.setText(user.getFname());
            t1_lname.setText(user.getLname());
            t1_email.setText(user.getEmail());
            tl_ekipn.setText(user.getEkipname());
            tl_sifre.setText(user.getSifre());
        }
        return convertView;
    }
}
