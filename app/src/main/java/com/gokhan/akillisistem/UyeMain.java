package com.gokhan.akillisistem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gokhan.akillisistem.AdminPaneli.MesajGoruntule;
import com.gokhan.akillisistem.UyeIslemleri.BarkodOkuma;
import com.gokhan.akillisistem.UyeIslemleri.GorevListele;
import com.gokhan.akillisistem.UyeIslemleri.MesajYolla;
import com.gokhan.akillisistem.UyeIslemleri.ProfilGoruntule;
import com.gokhan.akillisistem.UyeIslemleri.ToplantiGoruntule;
import com.gokhan.akillisistem.UyeIslemleri.uyeMesajGoruntule;

public class UyeMain extends AppCompatActivity {
    Database db;
    long mUserid;
    Button profil,gorevAl,barkodAct,msjOku,toplanti,cikis;
    Button mesaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_main);
        profil = (Button)findViewById(R.id.profil);
        mesaj  = (Button)findViewById(R.id.btn_msj);
        msjOku =(Button)findViewById(R.id.mesajoku);
        gorevAl = (Button)findViewById(R.id.gorev_durumu_btn);
        barkodAct=(Button)findViewById(R.id.btn_barkod);
        toplanti =(Button)findViewById(R.id.toplanti_btn);
        cikis =(Button)findViewById(R.id.cikis_uye_btn);
        db = new Database(this);

        mUserid = this.getIntent().getLongExtra(MainActivity.INTENTKEY_USERID,-1);
        if(mUserid>0){
            Log.d("LOGINSTATUS","Successfully logged in with userid " + String.valueOf(mUserid));
            Toast.makeText(getApplicationContext(),"BAŞARILI ID : "+mUserid,Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("LOGINSTATUS","NOT LOGGED IN");
        }



        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UyeMain.this, ProfilGoruntule.class);
                i.putExtra("id",(int)mUserid);
                startActivity(i);
            }
        });
        mesaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UyeMain.this, MesajYolla.class);
                i.putExtra("id",(int)mUserid);
                startActivity(i);
            }
        });
        gorevAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UyeMain.this, GorevListele.class);
                i.putExtra("id",(int)mUserid);
                startActivity(i);

            }
        });
        barkodAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UyeMain.this, BarkodOkuma.class);
                i.putExtra("id",(int)mUserid);
                startActivity(i);

            }
        });
        msjOku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UyeMain.this, uyeMesajGoruntule.class);
                i.putExtra("id",(int)mUserid);
                startActivity(i);
            }
        });
        toplanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UyeMain.this, ToplantiGoruntule.class);
                i.putExtra("id",(int)mUserid);
                startActivity(i);
            }
        });
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(UyeMain.this, MainActivity.class);
                startActivity(g);
            }
        });

    }
}
