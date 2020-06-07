package com.gokhan.akillisistem.Adapters.Uye;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gokhan.akillisistem.AdminPaneli.MesajCevap;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.adminMesaj;
import com.gokhan.akillisistem.R;
import com.gokhan.akillisistem.UyeIslemleri.MesajYolla;

import java.util.ArrayList;

public class adminMesajAdapter extends BaseAdapter {
    Context context;
    ArrayList<adminMesaj> arrayList;
    Database db;

    public adminMesajAdapter(Context context, ArrayList<adminMesaj> arrayList) {
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
            convertView = inflater.inflate(R.layout.row2, null);
            TextView tl_baslik = convertView.findViewById(R.id.baslik2);
            TextView tl_icerik = convertView.findViewById(R.id.icerik2);

            adminMesaj adminMesaj = arrayList.get(position);
            tl_baslik.setText(adminMesaj.getBaslik());
            tl_icerik.setText(adminMesaj.getIcerik());
            Button cvp = convertView.findViewById(R.id.row2_cvp);
            cvp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = new Database(context);
                    int idal = arrayList.get(position).getgId();
                    Toast.makeText(context,"ID : "+idal,Toast.LENGTH_SHORT).show();

                    Intent g = new Intent(v.getContext(), MesajCevap.class);
                    g.putExtra("id",idal);
                    Toast.makeText(context,"ID : "+idal,Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(g);

                }
            });



        }
        return convertView;
    }
}