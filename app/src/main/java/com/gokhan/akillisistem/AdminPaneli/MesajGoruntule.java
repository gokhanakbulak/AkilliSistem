package com.gokhan.akillisistem.AdminPaneli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.gokhan.akillisistem.Adapters.MesajAdapter;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Mesaj;
import com.gokhan.akillisistem.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MesajGoruntule extends AppCompatActivity {
    ListView listView;
    Database db;
    ArrayList<Mesaj> arrayList;
    MesajAdapter mesajAdapter;
    Button cvp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_goruntule);
        listView = findViewById(R.id.mesaj_listview);
        cvp = (Button)findViewById(R.id.gorev_sil_btn);
        db = new Database(this);
        arrayList = new ArrayList<>();
        loadDataInListView();
        


    }

    private void loadDataInListView() {
        arrayList  = db.getAllMesajData();
        mesajAdapter = new MesajAdapter(this,arrayList);
        listView.setAdapter(mesajAdapter);
        mesajAdapter.notifyDataSetChanged();
    }
}
