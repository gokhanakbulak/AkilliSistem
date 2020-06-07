package com.gokhan.akillisistem.UyeIslemleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.gokhan.akillisistem.Adapters.Uye.adminMesajAdapter;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Mesaj;
import com.gokhan.akillisistem.Model.adminMesaj;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class uyeMesajGoruntule extends AppCompatActivity {
    ListView listView;
    Database db;
    ArrayList<adminMesaj> arrayList;
    adminMesajAdapter adminmesajAdapter;
    Button row2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_mesaj_goruntule);
        listView = findViewById(R.id.uyemesajlist);
        row2 =(Button)findViewById(R.id.row2_cvp);
        db = new Database(this);
        arrayList = new ArrayList<>();
        loadDataInListView();


    }


    private void loadDataInListView() {
        arrayList  = db.getAllAdminMesajData();
        adminmesajAdapter= new adminMesajAdapter(this,arrayList);
        listView.setAdapter(adminmesajAdapter);
        adminmesajAdapter.notifyDataSetChanged();
    }

}
