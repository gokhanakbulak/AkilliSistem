package com.gokhan.akillisistem.AdminPaneli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gokhan.akillisistem.Adapters.UserAdapter;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.User;
import com.gokhan.akillisistem.Model.UserEkip;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class UyeListele extends AppCompatActivity {
    Database db;
    ListView li;
    ArrayList<UserEkip> arrayList;
    UserAdapter userAdapter;
    Button sil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_listele);
        li = (ListView)findViewById(R.id.listuye);
        sil = (Button)findViewById(R.id.sil_btn);
        db = new Database(this);
        arrayList = new ArrayList<>();
        loadDataInListView();



    }


    public void loadDataInListView() {
        arrayList= db.getAllData();
        userAdapter = new UserAdapter(this,arrayList);
        li.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();



    }
}
