package com.gokhan.akillisistem.AdminPaneli;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gokhan.akillisistem.AdminMain;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.UserToplanti;
import com.gokhan.akillisistem.R;

import java.util.Calendar;
import java.util.List;

public class ToplantiOlustur extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public List<String> uyelist;
    public TextView mDisplayDate;
    public DatePickerDialog.OnDateSetListener mDataSetListener;
    Database db;
    EditText tIsmi;
    Spinner uSpinner;
    Button toplantiOlustur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new Database(this);
        setContentView(R.layout.activity_toplanti_olustur);
        tIsmi = (EditText) findViewById(R.id.toplantiIsmi);
        uSpinner = (Spinner) findViewById(R.id.uyespinner);
        toplantiOlustur = (Button) findViewById(R.id.toplantiOlustur);
        uSpinner.setOnItemSelectedListener(this);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ToplantiOlustur.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);

            }
        };

        loadSpinnerData();
        toplantiOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelenToplantiIsmi = tIsmi.getText().toString();
                String tarih = mDisplayDate.getText().toString();
                int gelenToplantiid = ((UserToplanti) uSpinner.getSelectedItem()).getId();
                if (gelenToplantiIsmi.equals("")) {
                    Toast.makeText(getApplicationContext(), "Toplantı ismi boş bırakılamaz", Toast.LENGTH_SHORT).show();
                } else {


                    Boolean toplantiKaydet = db.toplantiKaydet(gelenToplantiIsmi, tarih, gelenToplantiid);
                    if (toplantiKaydet == true) {
                        Toast.makeText(getApplicationContext(), "Kaydedildi", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ToplantiOlustur.this, AdminMain.class);
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
        uSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(15);
        UserToplanti user = (UserToplanti) parent.getSelectedItem();


        Toast.makeText(parent.getContext(), "You selected: " + user.getName() + " ID: " + user.getId(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
