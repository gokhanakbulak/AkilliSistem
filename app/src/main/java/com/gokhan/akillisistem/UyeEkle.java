package com.gokhan.akillisistem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gokhan.akillisistem.Model.Ekip;

import java.util.List;


public class UyeEkle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Database db;
    Spinner ekipspinner;
    private EditText fname, lname, email, password;
    private Button btnKaydet;
    private List<String> ekip_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ekle);

        db = new Database(this);
        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        ekipspinner = (Spinner) findViewById(R.id.ekipspinner);
        btnKaydet = (Button) findViewById(R.id.kaydet);
        ekipspinner.setOnItemSelectedListener(this);

        loadSpinnerData();
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelenFname = fname.getText().toString();
                String gelenLname = lname.getText().toString();
                String gelenEmail = email.getText().toString();
                String gelenPass = password.getText().toString();

                int gelenEkipid = ((Ekip) ekipspinner.getSelectedItem()).getId();
                if (gelenEmail.equals("") || gelenPass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkemail = db.checkemail(gelenEmail);
                    if (checkemail == true) {
                        Boolean veriekle = db.veriekle(gelenFname, gelenLname, gelenEmail, gelenPass, gelenEkipid);
                        if (veriekle == true) {
                            Toast.makeText(getApplicationContext(), "Kaydedildi", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UyeEkle.this, AdminMain.class);
                            startActivity(i);
                        }
                    }
                }
            }
        });
    }

    private void loadSpinnerData() {
        Database db = new Database(getApplicationContext());
        List<Ekip> lables = db.getAllLabels();
        ArrayAdapter<Ekip> dataAdapter = new ArrayAdapter<Ekip>(this, android.R.layout.simple_spinner_item, lables);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ekipspinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        //String label = parent.getItemAtPosition(position).toString();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(15);
        Ekip ekip = (Ekip) parent.getSelectedItem();


        Toast.makeText(parent.getContext(), "You selected: " + ekip.getName() + " ID: " + ekip.getId(),
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
