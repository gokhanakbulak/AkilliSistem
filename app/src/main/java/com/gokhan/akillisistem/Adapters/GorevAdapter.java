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
import com.gokhan.akillisistem.Model.Gorev;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class GorevAdapter extends BaseAdapter {
    Context context;
    ArrayList<Gorev> arrayList;
    Database db;

    public GorevAdapter(Context context, ArrayList<Gorev> arrayList) {
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
            convertView = inflater.inflate(R.layout.mygorevlistlayout, null);


            TextView t1_id = convertView.findViewById(R.id.gorev_id_txt);
            TextView t1_ename = convertView.findViewById(R.id.gorev_name_txt);
            TextView t1_gDesc = convertView.findViewById(R.id.gorev_desc_txt);
            TextView t1_gZ = convertView.findViewById(R.id.gorev_zorluk_txt);
            TextView t1_gSure = convertView.findViewById(R.id.gorev_sure_txt);
            TextView tl_gSkills = convertView.findViewById(R.id.gorev_skills_txt);
            TextView tl_gIsim = convertView.findViewById(R.id.gorev_isim_txt);
            Button sil = convertView.findViewById(R.id.gorev_sil_btn);

            Gorev gorev = arrayList.get(position);
            t1_id.setText(String.valueOf(gorev.getId()));
            t1_ename.setText(gorev.getgName());
            t1_gDesc.setText(gorev.getgDesc());
            t1_gZ.setText(gorev.getzLev());
            t1_gSure.setText(gorev.gettTime());
            tl_gSkills.setText(gorev.getSkills());
            tl_gIsim.setText(gorev.getUserName());



            sil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    db = new Database(context);
                    int idal = arrayList.get(position).getId();

                    db.gorevSil(idal);


                }
            });
        }
            return convertView;
        }
    }