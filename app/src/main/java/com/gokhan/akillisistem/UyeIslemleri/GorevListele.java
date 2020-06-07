package com.gokhan.akillisistem.UyeIslemleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.gokhan.akillisistem.Adapters.GorevAdapter;
import com.gokhan.akillisistem.Adapters.Uye.UyeGorevListAdapter;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Gorev;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class GorevListele extends AppCompatActivity {
    Database db;
    ListView li;
    ArrayList<Gorev> arrayList;
    UyeGorevListAdapter gorevAdapter;
    Button grv_bitti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorev_listele);
        grv_bitti=(Button)findViewById(R.id.gorev_bitti_btn);
        Intent intent = getIntent();
        li = (ListView)findViewById(R.id.uyegorevlist);

        db = new Database(this);
        arrayList = new ArrayList<>();
        loadDataInListView();

    }

    private void loadDataInListView() {
        int idal = getIntent().getExtras().getInt("id");
        arrayList = db.getAllGorevDataById(idal);
        gorevAdapter = new UyeGorevListAdapter(this,arrayList);
        li.setAdapter(gorevAdapter);
        gorevAdapter.notifyDataSetChanged();
    }

}
