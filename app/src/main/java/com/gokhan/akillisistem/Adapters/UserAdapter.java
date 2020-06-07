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
import com.gokhan.akillisistem.Model.UserEkip;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    Context context;
    ArrayList<UserEkip> arrayList;
    Database db;


    public UserAdapter(Context context, ArrayList<UserEkip> arrayList) {
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
            convertView = inflater.inflate(R.layout.mycustomlayout, null);


            TextView t1_id = convertView.findViewById(R.id.id_txt);
            TextView t1_fname = convertView.findViewById(R.id.name_txt);
            TextView t1_lname = convertView.findViewById(R.id.lname_txt);
            TextView t1_email = convertView.findViewById(R.id.email_txt);
            TextView tl_ekipn = convertView.findViewById(R.id.ekip_txt);
            TextView tl_puan = convertView.findViewById(R.id.puan_txt);
            Button sil = convertView.findViewById(R.id.sil_btn);

            UserEkip user = arrayList.get(position);
            t1_id.setText(String.valueOf(user.getId()));
            t1_fname.setText(user.getFname());
            t1_lname.setText(user.getLname());
            t1_email.setText(user.getEmail());
            tl_ekipn.setText(user.getEkipname());
            tl_puan.setText(user.getPuan());

            sil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    db = new Database(context);
                    int idal = arrayList.get(position).getId();
                    Toast.makeText(context, "buton id : " + idal, Toast.LENGTH_SHORT).show();
                    db.calisanSil(idal);


                }
            });


        }
        return convertView;


    }


}
