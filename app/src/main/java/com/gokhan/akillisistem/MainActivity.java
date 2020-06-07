package com.gokhan.akillisistem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gokhan.akillisistem.Model.User;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    public static String INTENTKEY_USERID;
    Button login;
    EditText e,p;
    Database db;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e = (EditText) findViewById(R.id.emaillog);
        p  = (EditText) findViewById(R.id.passwordlog);
        login = (Button) findViewById(R.id.login);
        db = new Database(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = e.getText().toString();
                String sifre = p.getText().toString();
                if(email.equals("admin") && sifre.equals("1")){
                    Toast.makeText(getApplicationContext(),"Hoşgeldin Admin",Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(MainActivity.this,AdminMain.class);
                    startActivity(i2);
                    return;
                }
                long userid = db.getUser(email,sifre);

                if(userid<1){
                    Toast.makeText(getApplicationContext(),"Loggin Failed",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(MainActivity.this,UyeMain.class);
                i.putExtra(INTENTKEY_USERID,userid);
                startActivity(i);




                /*long GirisKontrol = db.getUser(email,password);

                if(GirisKontrol==true){
                    Toast.makeText(getApplicationContext(),"Giriş Başarılı..",Toast.LENGTH_SHORT).show();
                    User user = new User();
                    int idal = user.getId();
                    Toast.makeText(getApplicationContext(),"ID "+idal,Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,UyeMain.class);
                    startActivity(i);
                }
                    */

             /*   if(email.equals("admin") && password.equals("1234"))
                {
                    Toast.makeText(getApplicationContext(),"Hoşgeldin Admin",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,AdminMain.class);
                    startActivity(i);

                }*/


            }
        });
    }
}
