package com.gokhan.akillisistem.AdminPaneli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gokhan.akillisistem.AdminMain;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.UserToplanti;
import com.gokhan.akillisistem.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GorevOlustur extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{
    Database db;
    Spinner uyeSpin;
    EditText gName,gDesc,skills,tTime,zLev;
    Button gOlustur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorev_olustur);


        db= new Database(this);
        gName=(EditText)findViewById(R.id.gName);
        gDesc=(EditText)findViewById(R.id.gDesc);
        skills=(EditText)findViewById(R.id.skills);
        tTime=(EditText)findViewById(R.id.tTime);
        zLev=(EditText)findViewById(R.id.zLev);
        gOlustur=(Button)findViewById(R.id.btn_gorev);

        uyeSpin = (Spinner)findViewById(R.id.gorevspinner);

        uyeSpin.setOnItemSelectedListener(this);
        loadSpinnerData();

        gOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelenGorevIsmi = gName.getText().toString();
                String gelenGorevDesc = gDesc.getText().toString();
                String gelenSkills = skills.getText().toString();
                String gelenTime = tTime.getText().toString();
                String gelenZ = zLev.getText().toString();
                int gelenUyeId = ((UserToplanti) uyeSpin.getSelectedItem()).getId();
                Date currentTime = Calendar.getInstance().getTime();
                if (gelenGorevIsmi.equals("")) {
                    Toast.makeText(getApplicationContext(), "Görev ismi boş bırakılamaz", Toast.LENGTH_SHORT).show();

                } else {


                    Boolean gorevOlustur = db.gorevOlustur(gelenGorevIsmi, gelenGorevDesc, gelenZ,gelenTime,gelenSkills,gelenUyeId);
                    if (gorevOlustur == true) {
                        Toast.makeText(getApplicationContext(), "Kaydedildi", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(GorevOlustur.this, AdminMain.class);
                        startActivity(i);
                    }
                }
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
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {


        UserToplanti user = (UserToplanti) parent.getSelectedItem();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(15);





    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
