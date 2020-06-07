package com.gokhan.akillisistem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gokhan.akillisistem.AdminPaneli.AdminEkipOlustur;
import com.gokhan.akillisistem.AdminPaneli.GorevListesi;
import com.gokhan.akillisistem.AdminPaneli.GorevOlustur;
import com.gokhan.akillisistem.AdminPaneli.GrafikAnaEkran;
import com.gokhan.akillisistem.AdminPaneli.GrafikIslem;
import com.gokhan.akillisistem.AdminPaneli.MesajGoruntule;
import com.gokhan.akillisistem.AdminPaneli.ToplantiOlustur;
import com.gokhan.akillisistem.AdminPaneli.UyeListele;

public class AdminMain extends AppCompatActivity {
    public Button uyeekle,grafikIslem;
    Button ekipolustur,uyelistele,toplantiOlustur,gorevOlustur,gorevListesi,mesajGoruntule,cikis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        uyeekle = (Button)findViewById(R.id.uyeekle);
        uyelistele = (Button)findViewById(R.id.uyelistele);
        cikis =(Button)findViewById(R.id.cikis_btn);
        ekipolustur = (Button)findViewById(R.id.ekiolusturbutton);
        mesajGoruntule=(Button)findViewById(R.id.mesaj_goruntule);
        toplantiOlustur = (Button)findViewById(R.id.toplantiOlustur);
        gorevOlustur = (Button) findViewById(R.id.admin_gorev_olustur);
        gorevListesi = (Button) findViewById(R.id.gorev_listesi_btn);
        grafikIslem =(Button)findViewById(R.id.grafik);
        ekipolustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ekip = new Intent(AdminMain.this, AdminEkipOlustur.class);
                startActivity(ekip);
            }
        });

        uyeekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uye = new Intent(AdminMain.this,UyeEkle.class);
                startActivity(uye);
            }
        });
        uyelistele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent liste = new Intent(AdminMain.this, UyeListele.class);
                startActivity(liste);
            }
        });
        toplantiOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toplanti = new Intent(AdminMain.this, ToplantiOlustur.class);
                startActivity(toplanti);
            }
        });
        gorevOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gorev = new Intent(AdminMain.this, GorevOlustur.class);
                startActivity(gorev);
            }
        });
        gorevListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent liste = new Intent(AdminMain.this, GorevListesi.class);
                startActivity(liste);
            }
        });
        mesajGoruntule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mesaj = new Intent(AdminMain.this, MesajGoruntule.class);
                startActivity(mesaj);
            }
        });
        grafikIslem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(AdminMain.this, GrafikAnaEkran.class);
                startActivity(g);
            }
        });
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(AdminMain.this, MainActivity.class);
                startActivity(g);
            }
        });

    }

}
