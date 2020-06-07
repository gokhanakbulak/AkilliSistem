package com.gokhan.akillisistem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Ekip;
import com.gokhan.akillisistem.Model.UserEkip;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class EkipAdapter extends BaseAdapter {
    Context context;
    ArrayList<Ekip> arrayList;
    Database db;


    public EkipAdapter(Context context, ArrayList<Ekip> arrayList) {
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
            convertView = inflater.inflate(R.layout.myekiplistlayout, null);


            TextView t1_id = convertView.findViewById(R.id.ekipid_txt);
            TextView t1_ename = convertView.findViewById(R.id.ekipname_txt);

            Button sil = convertView.findViewById(R.id.sil_btn_ekip);

            Ekip user = arrayList.get(position);
            t1_id.setText(String.valueOf(user.getId()));
            t1_ename.setText(user.getName());



            sil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    db = new Database(context);
                    int idal = arrayList.get(position).getId();
                    Toast.makeText(context, "buton id : " + idal, Toast.LENGTH_SHORT).show();
                    db.ekipSil(idal);


                }
            });


        }
        return convertView;


    }

}
