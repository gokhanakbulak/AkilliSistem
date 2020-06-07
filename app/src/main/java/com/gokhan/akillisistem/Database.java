package com.gokhan.akillisistem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.gokhan.akillisistem.Model.Chart;
import com.gokhan.akillisistem.Model.Ekip;
import com.gokhan.akillisistem.Model.Gorev;
import com.gokhan.akillisistem.Model.Mesaj;
import com.gokhan.akillisistem.Model.Toplanti;
import com.gokhan.akillisistem.Model.UserEkip;
import com.gokhan.akillisistem.Model.UserToplanti;
import com.gokhan.akillisistem.Model.adminMesaj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Database extends SQLiteOpenHelper {

    private static final String COLUMN_USER_ID = "id";

    private static final String DATABASE_NAME = "wisecompany";
    private static final int DATABASE_VERSION = 1;
    private Context context;


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT ,fname text,lname text,email text,password text,ekip_id INTEGER,puan  INTEGER DEFAULT 50)");
        db.execSQL("CREATE TABLE ekip(id INTEGER PRIMARY KEY AUTOINCREMENT ,ekipname text)");
        db.execSQL("CREATE TABLE toplanti(id INTEGER PRIMARY KEY AUTOINCREMENT ,tName text,tDate text,userId INTEGER)");
        db.execSQL("CREATE TABLE gorev(id INTEGER PRIMARY KEY AUTOINCREMENT ,gName text,gDesc text,zLev INTEGER,tTime Integer,skils text,userId INTEGER,gDurum text,gTarih text)");
        db.execSQL("CREATE TABLE mesaj(id INTEGER PRIMARY KEY AUTOINCREMENT ,baslik text,icerik text,gId INTEGER)");
        db.execSQL("CREATE TABLE adminmesaj(id INTEGER PRIMARY KEY AUTOINCREMENT ,baslik text,icerik text,alanId INTEGER,gId INTEGER)");
        db.execSQL("CREATE TABLE barkod(id INTEGER PRIMARY KEY AUTOINCREMENT ,durum text,tarih text,userId INTEGER)");
        db.execSQL("CREATE TABLE puan(id INTEGER PRIMARY KEY AUTOINCREMENT ,userId INTEGER,saat text,verilenGorev text,gorevPuan INTEGER,tarih text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS user ");
        db.execSQL("DROP TABLE IF EXISTS ekip ");
        db.execSQL("DROP TABLE IF EXISTS toplanti ");
        db.execSQL("DROP TABLE IF EXISTS gorev");
        db.execSQL("DROP TABLE IF EXISTS mesaj");
        db.execSQL("DROP TABLE IF EXISTS barkod");
        db.execSQL("DROP TABLE IF EXISTS puan");
        db.execSQL("DROP TABLE IF EXISTS adminmesaj");
        onCreate(db);
    }

    /* BARKOD OKUMA */
    public Boolean barkodOlustur(String durum, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        cv.put("durum", durum);
        cv.put("tarih", date);
        cv.put("userId", userId);
        long ins = db.insert("barkod", null, cv);
        return ins != -1;
    }
    public Boolean adminMesajOlustur(String baslik, String icerik, int alanId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("baslik", baslik);
        cv.put("icerik", icerik);
        cv.put("alanId", alanId);
        cv.put("gId",2);
        long ins = db.insert("adminmesaj", null, cv);
        return ins != -1;
    }

    public Boolean mesajOlustur(String baslik, String icerik, int gId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("baslik", baslik);
        cv.put("icerik", icerik);
        cv.put("gId", gId);
        long ins = db.insert("mesaj", null, cv);
        return ins != -1;
    }

    public Boolean veriekle(String fname, String lname, String email, String pass, int ekip_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("password", pass);
        contentValues.put("ekip_id", ekip_id);


        long ins = db.insert("user", null, contentValues);
        return ins != -1;

    }

    public Boolean toplantiKaydet(String tname, String tDate, int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tName", tname);
        contentValues.put("tDate", tDate);
        contentValues.put("userId", userid);


        long ins = db.insert("toplanti", null, contentValues);
        return ins != -1;

    }

    /* gorev(id INTEGER PRIMARY KEY AUTOINCREMENT ,gName text,gDesc text,zLev INTEGER,tTime Integer,skils text,userId INTEGER) */
    public Boolean gorevOlustur(String gName, String gDesc, String zLev, String tTime, String skils, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(calendar.getTime());
        ContentValues contentValues = new ContentValues();
        contentValues.put("gName", gName);
        contentValues.put("gDesc", gDesc);
        contentValues.put("zLev", zLev);
        contentValues.put("tTime", tTime);
        contentValues.put("skils", skils);
        contentValues.put("userId", userId);
        contentValues.put("gDurum", false);
        contentValues.put("gTarih", time);

        long ins = db.insert("gorev", null, contentValues);
        return ins != -1;


    }

    public void profilGoruntule(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE  id=?", new String[]{String.valueOf(id)});


    }

    public Boolean checkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        return cursor.getCount() <= 0;

    }


    public long getUser(String email, String password) {
        long rv = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor csr = db.rawQuery("select id,email,password from user where email=? and password=?", new String[]{email, password});
        if (csr.moveToFirst()) {
            rv = csr.getLong(csr.getColumnIndex("id"));
        }
        csr.close();
        return rv;
    }

    /* EKİP OLUŞTURMA  */
    public Boolean ekipEkle(String ekipname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ekipname", ekipname);


        long ins = db.insert("ekip", null, contentValues);
        return ins != -1;

    }

    public List<Ekip> getAllLabels() {
        List<Ekip> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  *  FROM  ekip";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ekip ekip = new Ekip();
                ekip.setId(Integer.parseInt(cursor.getString(0)));
                ekip.setName(cursor.getString(1));

                labels.add(ekip);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    /*  Mesaj Listeleme */
    public ArrayList<Mesaj> getAllMesajData() {
        ArrayList<Mesaj> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT baslik,icerik,fname,m.gId FROM mesaj m JOIN user u ON m.gId = u.id", null);
        while (cursor.moveToNext()) {
            String baslik = cursor.getString(0);
            String icerik = cursor.getString(1);
            String uName = cursor.getString(2);
            int gId = cursor.getInt(3);
            Mesaj mesaj = new Mesaj(baslik, icerik, uName,gId);
            arrayList.add(mesaj);

        }
        return arrayList;
    }
    public ArrayList<adminMesaj> getAllAdminMesajData() {
        ArrayList<adminMesaj> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT baslik,icerik FROM adminmesaj WHERE gId='2'", null);
        while (cursor.moveToNext()) {
            String baslik = cursor.getString(0);
            String icerik = cursor.getString(1);

            adminMesaj mesaj = new adminMesaj(baslik, icerik);
            arrayList.add(mesaj);

        }
        return arrayList;
    }

    /* Profil Listele */
    public ArrayList<UserEkip> getAllProfilData(int gId) {
        ArrayList<UserEkip> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT u.id,fname,lname,email,ekipname,password FROM user u JOIN ekip e ON u.ekip_id = e.id WHERE u.id=?", new String[]{String.valueOf(gId)});
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String fname = cursor.getString(1);
            String lname = cursor.getString(2);
            String email = cursor.getString(3);
            String ekipn = cursor.getString(4);
            String pass = cursor.getString(5);
            UserEkip user = new UserEkip(id, fname, lname, email, pass, ekipn);
            arrayList.add(user);


        }
        return arrayList;

    }
    /*GÖREV BİTTİ BUTON METHODU */

    public void GorevBitti(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String table_name = "gorev";
        ContentValues cv = new ContentValues();
        cv.put("gDurum", true);
        db.update(table_name, cv, "id=?", new String[]{String.valueOf(id)});
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(calendar.getTime());
        puanHesapla(id, time);


    }
    /* PUAN HESAPLA */

    public ArrayList<Gorev> puanHesapla(int id, String time) {
        ArrayList<Gorev> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT userId,gName,gTarih,zLev,tTime FROM gorev WHERE id=? ", new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {

            int idal = cursor.getInt(0);
            String gName = cursor.getString(1);
            String gTarih = cursor.getString(2);
            String zLev = cursor.getString(3);
            String tTime = cursor.getString(4);

            Gorev gorev = new Gorev(idal, gTarih, zLev, tTime,gName);
            arrayList.add(gorev);
            String time1 = gTarih;
            String time2 = time;


            LocalTime t1 = LocalTime.parse(time1);
            LocalTime t2 = LocalTime.parse(time2);
            Duration diff = Duration.between(t2, t1);

            int deger = (int) diff.toHours();
            int YenitTime = Integer.parseInt(tTime);
            int YenizLev = Integer.parseInt(zLev);
           // Toast.makeText(context,"İslemler yapilsin : "+diff.toHours(),Toast.LENGTH_SHORT).show();

            if(deger<=YenitTime){
             int zamanPuani = (YenitTime-deger)*10;
             int zorlukPuani= (YenizLev*10);
             int toplamPuan = (int) (  (zamanPuani+zorlukPuani)*0.1);
             puanEkle(idal,toplamPuan,gTarih,gName);
            }


        }

        return arrayList;

    }
    public Boolean puanEkle(int id,int toplam,String tarih,String name){

        SQLiteDatabase db = this.getWritableDatabase();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", id);
        contentValues.put("saat", tarih);
        contentValues.put("verilenGorev", name);
        contentValues.put("gorevPuan", toplam);
        contentValues.put("tarih",date);
        long ins = db.insert("puan", null, contentValues);
        kullaniciPuanGuncelleme(id,toplam);

        return ins != -1;

    }
    public void kullaniciPuanGuncelleme(int id,int toplam){
        SQLiteDatabase db = this.getWritableDatabase();
        String TABLE_NAME ="user";
        String TABLE_COL  ="puan";
        String TABLE_ID   ="id";
        db.execSQL("UPDATE " + TABLE_NAME + " SET "
                + TABLE_COL + " = " + TABLE_COL + " + " + toplam +
                " WHERE " + TABLE_ID + " = " + id);
    }

/* CHART  */
    /*public ArrayList<Chart> getChartData(int id) {
    ArrayList<Chart> arrayList = new ArrayList<>();

    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT p.tarih,p.gorevPuan,verilenGorev ,COUNT(u.id) AS Sayı FROM user u JOIN puan p ON u.id = p.userId WHERE u.id=?",new String[]{String.valueOf(id)});
    while (cursor.moveToNext()) {

        String tarih = cursor.getString(0);
        String gorevPuan = cursor.getString(1);
        String verilenGorev = cursor.getString(2);
        int sayi = cursor.getInt(3);
        Chart chart = new Chart(tarih,gorevPuan,verilenGorev,sayi);
        arrayList.add(chart);


    }
    return arrayList;

}*/




    /*   ÇALIŞANLARI LİSTELEME METHODU */

    public ArrayList<UserEkip> getAllData() {
        ArrayList<UserEkip> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String fname = cursor.getString(1);
            String lname = cursor.getString(2);
            String email = cursor.getString(3);
            String ekipn = cursor.getString(4);
            String puan = cursor.getString(5);
            String sifre = cursor.getString(6);
            UserEkip user = new UserEkip(id, fname, lname, email, ekipn,sifre,puan);
            arrayList.add(user);


        }
        return arrayList;

    }

    /*Ekip Listeleme */
    public ArrayList<Ekip> getAllEkipData() {
        ArrayList<Ekip> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ekip ", null);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String ename = cursor.getString(1);

            Ekip user = new Ekip(id, ename);
            arrayList.add(user);


        }
        return arrayList;

    }

    /* EKİP SİL */
    public void ekipSil(int id) {


        SQLiteDatabase db = this.getWritableDatabase();


        db.delete("ekip", "id=?", new String[]{String.valueOf(id)});
        Toast.makeText(context, "ID'si  " + id + " olan ekip silindi", Toast.LENGTH_SHORT).show();


    }
    public void gorevSil(int id) {


        SQLiteDatabase db = this.getWritableDatabase();


        db.delete("gorev", "id=?", new String[]{String.valueOf(id)});
        Toast.makeText(context, "ID'si  " + id + " olan gorev silindi", Toast.LENGTH_SHORT).show();


    }

    public void calisanSil(int id) {


        SQLiteDatabase db = this.getWritableDatabase();


        db.delete("user", "id=?", new String[]{String.valueOf(id)});
        Toast.makeText(context, "ID'si  " + id + " olan kullanıcı silindi", Toast.LENGTH_SHORT).show();


    }


    /* TOPLANTI SPINNER UYE AL  */
    public List<UserToplanti> getAllUyeLabels() {
        List<UserToplanti> labels = new ArrayList<>();


        String selectQuery = "SELECT  * FROM  user";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                UserToplanti user = new UserToplanti();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setLname(cursor.getString(2));
                labels.add(user);
            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();


        return labels;
    }
   public ArrayList<Toplanti> getAllToplantiData(int id){
        ArrayList<Toplanti> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT tName,tDate FROM toplanti WHERE userId=?",new String[]{String.valueOf(id)});
        while (cursor.moveToNext()){
            String tName = cursor.getString(0);
            String tDate = cursor.getString(1);
            Toplanti toplanti = new Toplanti(tName,tDate);
            arrayList.add(toplanti);
        }
        return arrayList;
   }

    public ArrayList<Gorev> getAllGorevData() {
        ArrayList<Gorev> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT g.id ,gName,gDesc,zLev,tTime,skils,fname FROM gorev g  JOIN user u ON g.userId = u.id WHERE gDurum=0 ", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String gName = cursor.getString(1);
            String gDesc = cursor.getString(2);
            String zLev = cursor.getString(3);
            String sure = cursor.getString(4);
            String skills = cursor.getString(5);
            String userName = cursor.getString(6);
            Gorev gorev = new Gorev(id, gName, gDesc, zLev, sure, skills, userName);
            arrayList.add(gorev);

        }
        return arrayList;
    }

    /* ÜYE DE GÖREV LİSTELEME */
    public ArrayList<Gorev> getAllGorevDataById(int Uid) {
        ArrayList<Gorev> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT g.id ,gName,gDesc,zLev,tTime,skils,fname FROM gorev g JOIN user u ON g.userId = u.id\n WHERE g.userId=? AND gDurum=0", new String[]{String.valueOf(Uid)});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String gName = cursor.getString(1);
            String gDesc = cursor.getString(2);
            String zLev = cursor.getString(3);
            String sure = cursor.getString(4);
            String skills = cursor.getString(5);
            String userName = cursor.getString(6);
            Gorev gorev = new Gorev(id, gName, gDesc, zLev, sure, skills, userName);
            arrayList.add(gorev);

        }
        return arrayList;
    }

}
