package com.gokhan.akillisistem.Adapters;

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
import com.gokhan.akillisistem.Model.Mesaj;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class MesajAdapter extends BaseAdapter {
    Context context;
    ArrayList<Mesaj> arrayList;
    Database db;
    public MesajAdapter(Context context,ArrayList<Mesaj> arrayList){
        this.context= context;
        this.arrayList=arrayList;
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
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, null);
            TextView tl_baslik = convertView.findViewById(R.id.baslik);
            TextView tl_icerik = convertView.findViewById(R.id.icerik);
            TextView tl_gond = convertView.findViewById(R.id.gonderen);
            TextView tl_gid = convertView.findViewById(R.id.admin_mesajgid);
            Button cvp = convertView.findViewById(R.id.cvp);

            Mesaj mesaj = arrayList.get(position);

            tl_baslik.setText(mesaj.getBaslik());
            tl_icerik.setText(mesaj.getIcerik());
            tl_gond.setText(mesaj.getuName());
            int idal = arrayList.get(position).getgId();




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
