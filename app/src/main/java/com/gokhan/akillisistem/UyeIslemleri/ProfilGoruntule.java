package com.gokhan.akillisistem.UyeIslemleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.gokhan.akillisistem.Adapters.ProfilGoruntuleAdapter;
import com.gokhan.akillisistem.Adapters.UserAdapter;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.UserEkip;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class ProfilGoruntule extends AppCompatActivity {
    Database db;
    ListView li;
    ArrayList<UserEkip> arrayList;
    ProfilGoruntuleAdapter profilAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_goruntule);
        Intent intent = getIntent();
        li = (ListView)findViewById(R.id.profilgoruntulelist);
        db = new Database(this);
        arrayList = new ArrayList<>();
        loadDataInListView();

        int idal = getIntent().getExtras().getInt("id");

        Toast.makeText(getApplicationContext(),"ID  : "+idal,Toast.LENGTH_SHORT).show();



    }

    private void loadDataInListView() {
        int idal=getIntent().getExtras().getInt("id");
        arrayList= db.getAllProfilData(idal);
        profilAdapter = new ProfilGoruntuleAdapter(this,arrayList);
        li.setAdapter(profilAdapter);
        profilAdapter.notifyDataSetChanged();
    }
}
