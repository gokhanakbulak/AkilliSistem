package com.gokhan.akillisistem.Adapters.Uye;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Gorev;
import com.gokhan.akillisistem.R;
import com.gokhan.akillisistem.UyeIslemleri.GorevListele;

import java.util.ArrayList;

public class UyeGorevListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Gorev> arrayList;
    Database db;

    public UyeGorevListAdapter(Context context, ArrayList<Gorev> arrayList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.myuyegorevlist, null);



            TextView t1_ename = convertView.findViewById(R.id.gl_name);
            TextView t1_gDesc = convertView.findViewById(R.id.gl_desc);
            TextView t1_gZ = convertView.findViewById(R.id.gl_zorluk);
            TextView t1_gSure = convertView.findViewById(R.id.gl_saat);
            TextView tl_gSkills = convertView.findViewById(R.id.gl_is);

            Button gorevBitti = convertView.findViewById(R.id.gorev_bitti_btn);

            Gorev gorev = arrayList.get(position);

            t1_ename.setText(gorev.getgName());
            t1_gDesc.setText(gorev.getgDesc());
            t1_gZ.setText(gorev.getzLev());
            t1_gSure.setText(gorev.gettTime());
            tl_gSkills.setText(gorev.getSkills());

            gorevBitti.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    db = new Database(context);

                    int idal = arrayList.get(position).getId();
                    db.GorevBitti(idal);

                }
            });

        }
        return convertView;
    }
}
