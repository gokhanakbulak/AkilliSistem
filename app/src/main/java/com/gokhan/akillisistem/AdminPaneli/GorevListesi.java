package com.gokhan.akillisistem.AdminPaneli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.gokhan.akillisistem.Adapters.GorevAdapter;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Gorev;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class GorevListesi extends AppCompatActivity {
    Database db;
    ListView li;
    ArrayList<Gorev> arrayList;
    GorevAdapter gorevAdapter;
    Button sil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorev_listesi);
        li = (ListView)findViewById(R.id.listgorev);
        sil = (Button)findViewById(R.id.gorev_sil_btn);
        db = new Database(this);
        arrayList = new ArrayList<>();
        loadDataInListView();
    }

    private void loadDataInListView() {
        arrayList = db.getAllGorevData();
        gorevAdapter = new GorevAdapter(this,arrayList);
        li.setAdapter(gorevAdapter);
        gorevAdapter.notifyDataSetChanged();

    }
}
