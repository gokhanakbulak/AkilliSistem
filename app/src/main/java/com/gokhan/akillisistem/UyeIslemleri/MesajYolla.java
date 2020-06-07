package com.gokhan.akillisistem.UyeIslemleri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.R;
import com.gokhan.akillisistem.UyeMain;

public class MesajYolla extends AppCompatActivity {
    EditText baslik,icerik;
    Button gonder;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_yolla);
        Intent intent = getIntent();
        gonder = (Button)findViewById(R.id.mesaj_yolla);
        baslik = (EditText)findViewById(R.id.mesaj_baslik);
        icerik = (EditText)findViewById(R.id.mesaj_icerik);
        db = new Database(this);

        final int idal = getIntent().getExtras().getInt("id");

        Toast.makeText(getApplicationContext(),"ID  : "+idal,Toast.LENGTH_SHORT).show();
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gelenBaslik = baslik.getText().toString();
                String gelenIcerik = icerik.getText().toString();
                if(baslik.equals("") && icerik.equals("")){
                    Toast.makeText(getApplicationContext(),"Başlık veya İçerik boş olamaz...",Toast.LENGTH_SHORT).show();

                }
                else{
                    db.mesajOlustur(gelenBaslik,gelenIcerik,idal);
                    Toast.makeText(getApplicationContext(),"Mesajınız yollandı...",Toast.LENGTH_SHORT).show();
                    Intent donus = new Intent(MesajYolla.this, UyeMain.class);
                    startActivity(donus);
                }
            }
        });



    }
}
