package com.gokhan.akillisistem.AdminPaneli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gokhan.akillisistem.AdminMain;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.R;

public class MesajCevap extends AppCompatActivity {
    Button yolla;
    EditText baslik,icerik;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_cevap);
        final int idal = getIntent().getExtras().getInt("id");
        Toast.makeText(getApplicationContext(),"ID  : "+idal,Toast.LENGTH_SHORT).show();
        yolla = (Button)findViewById(R.id.adminMyolla);
        baslik = (EditText)findViewById(R.id.adminMbaslik);
        icerik = (EditText)findViewById(R.id.adminMicerik);
        db = new Database(this);
        yolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gelenBaslik = baslik.getText().toString();
                String gelenIcerik = icerik.getText().toString();
                if(baslik.equals("") && icerik.equals("")){
                    Toast.makeText(getApplicationContext(),"Başlık veya İçerik boş olamaz...",Toast.LENGTH_SHORT).show();

                }
                else{
                    db.adminMesajOlustur(gelenBaslik,gelenIcerik,idal);
                    Toast.makeText(getApplicationContext(),"Mesajınız yollandı...",Toast.LENGTH_SHORT).show();
                    Intent donus = new Intent(MesajCevap.this, AdminMain.class);
                    startActivity(donus);
                }
            }
        });
    }
}
