package com.gokhan.akillisistem.UyeIslemleri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.gokhan.akillisistem.Adapters.ToplantiAdapter;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Toplanti;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;

public class ToplantiGoruntule extends AppCompatActivity {
    ListView listView;
    Database db;
    ArrayList<Toplanti> arrayList;
    ToplantiAdapter toplantiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toplanti_goruntule);
        listView = (ListView)findViewById(R.id.toplanti_list);
        int idal = getIntent().getExtras().getInt("id");
        Toast.makeText(getApplicationContext(),"ID "+idal,Toast.LENGTH_SHORT).show();
        db = new Database(this);
        arrayList = new ArrayList<>();
        loadDataInListView();
    }

    private void loadDataInListView() {

        int idal = getIntent().getExtras().getInt("id");
        arrayList = db.getAllToplantiData(idal);
        toplantiAdapter = new ToplantiAdapter(this,arrayList);
        listView.setAdapter(toplantiAdapter);
        toplantiAdapter.notifyDataSetChanged();

    }
}
