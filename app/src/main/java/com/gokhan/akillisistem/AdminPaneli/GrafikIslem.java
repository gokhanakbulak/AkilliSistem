package com.gokhan.akillisistem.AdminPaneli;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.gokhan.akillisistem.Database;
import com.gokhan.akillisistem.Model.Chart;
import com.gokhan.akillisistem.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafikIslem extends AppCompatActivity {
    Database db;
    long gelenUserId;
    public BarChart barChart;
    List<Chart> listchart = new ArrayList<>();
    List<BarEntry> barlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_islem);
        barChart = (BarChart)findViewById(R.id.barchart);



        dataGir();
        for(int i=0; i<listchart.size();i++){
            barlist.add(new BarEntry(i,Float.parseFloat(listchart.get(i).getGorevPuan())));
        }
        BarDataSet datas = new BarDataSet(barlist,"Grafik");
        BarData  data = new BarData(datas);
        datas.setColors(ColorTemplate.COLORFUL_COLORS);

        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter();
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(formatter);

        data.setBarWidth(0.9f);
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.invalidate();




    }

    private void dataGir() {
        listchart.clear();
        Database db = new Database(this);
        SQLiteDatabase database = db.getWritableDatabase();
        int idal = getIntent().getExtras().getInt("id");

        Toast.makeText(getApplicationContext(),"ID  : "+idal,Toast.LENGTH_SHORT).show();
        Cursor fila =  database.rawQuery("SELECT gorevPuan,verilenGorev from puan WHERE userId=?",new String[]{String.valueOf(idal)});
        if(fila != null && fila.getCount() != 0) {
            fila.moveToFirst();
            do {
                listchart.add(
                        new Chart(
                                fila.getString(0),
                                fila.getString(1)
                        )
                );
            } while(fila.moveToNext());
        } else {
            Toast.makeText(this, "Grafik için veri yok....", Toast.LENGTH_SHORT).show();
        }

        db.close();

    }

}
