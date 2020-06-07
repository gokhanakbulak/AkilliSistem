package com.gokhan.akillisistem.AdminPaneli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gokhan.akillisistem.Adapters.EkipAdapter;
import com.gokhan.akillisistem.AdminMain;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Ekip;
import com.gokhan.akillisistem.R;


import java.util.ArrayList;

import static android.R.layout.simple_spinner_dropdown_item;

public class AdminEkipOlustur extends AppCompatActivity {
    Database db;
    EditText ekipismi;
    Button olustur,sil;
    ListView li;
    EkipAdapter ekipAdapter;
    ArrayList<Ekip> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ekip_olustur);
        db = new Database(this);
        li = (ListView)findViewById(R.id.ekiplist);
        ekipismi=(EditText)findViewById(R.id.ekipismi);
        olustur=(Button)findViewById(R.id.ekipbutton);
        sil  =(Button)findViewById(R.id.sil_btn_ekip);
        loadDataInEkipListView();
        olustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelenEkip = ekipismi.getText().toString();
                if(gelenEkip.equals(""))
                    Toast.makeText(getApplicationContext(),"Ekip ismi boş bırakılamaz",Toast.LENGTH_SHORT).show();
                Boolean ekipekle = db.ekipEkle(gelenEkip);
                if(ekipekle==true)
                    Toast.makeText(getApplicationContext(),"Ekip oluşturma başarılı",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AdminEkipOlustur.this, AdminMain.class);
                    startActivity(i);
            }
        });



    }

    private void loadDataInEkipListView() {
        arrayList= db.getAllEkipData();
        ekipAdapter = new EkipAdapter(this,arrayList);
        li.setAdapter(ekipAdapter);
        ekipAdapter.notifyDataSetChanged();

    }


}
