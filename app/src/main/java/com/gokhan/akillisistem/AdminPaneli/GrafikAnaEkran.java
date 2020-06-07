package com.gokhan.akillisistem.AdminPaneli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gokhan.akillisistem.AdminMain;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.UserToplanti;
import com.gokhan.akillisistem.R;

import java.util.List;

public class GrafikAnaEkran extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Button grafik;
    Spinner uyeSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_ana_ekran);
        grafik=(Button)findViewById(R.id.btn_grafik_olustur);
        uyeSpin = (Spinner)findViewById(R.id.grafik_kullanici_spinner);
        uyeSpin.setOnItemSelectedListener(this);
        loadSpinnerData();
        grafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gelenUyeId = ((UserToplanti) uyeSpin.getSelectedItem()).getId();

                Toast.makeText(getApplicationContext(), "ID = "+ gelenUyeId, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(GrafikAnaEkran.this, GrafikIslem.class);
                i.putExtra("id",gelenUyeId);
                startActivity(i);
            }
        });


    }

    private void loadSpinnerData() {
        Database db = new Database(getApplicationContext());
        List<UserToplanti> lables = db.getAllUyeLabels();
        ArrayAdapter<UserToplanti> dataAdapter = new ArrayAdapter<UserToplanti>(this, android.R.layout.simple_spinner_item, lables);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uyeSpin.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(15);
        UserToplanti user = (UserToplanti) parent.getSelectedItem();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
