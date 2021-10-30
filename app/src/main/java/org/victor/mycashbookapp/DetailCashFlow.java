package org.victor.mycashbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.victor.mycashbookapp.Helper.helperr;
import org.victor.mycashbookapp.Model.data;

import java.util.ArrayList;

public class DetailCashFlow extends AppCompatActivity {
    helperr helper;
    Button btnKembali;
    ArrayList<data> dataArrayList;
    //CustomAdapter customAdapter;
    ListView listView;
//    Object CustomAdapter;
//    private Object data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cash_flow);
        btnKembali = findViewById(R.id.btnBack);
        listView = (ListView) findViewById(R.id.listViewItem);
        helper = new helperr(DetailCashFlow.this);

        dataArrayList = new ArrayList<>();
        Cursor cursor = helper.readAllData();
        dataArrayList.clear();
        while (cursor.moveToNext()) {
            dataArrayList.add(new data(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4),cursor.getString(5)));
        }
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),R.layout.item_detail,dataArrayList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listView.setAdapter(customAdapter);


//        simbol = new ArrayList<>();
//        nominal = new ArrayList<>();
//        keterangan = new ArrayList<>();
//        tgl = new ArrayList<>();
//        status = new ArrayList<>();
//        display();
//        CustomAdapter = new CustomAdapter(getApplicationContext(), R.layout.item_detail, ArrayList <String> simbol,
//                ArrayList <String> nominal, ArrayList >String> keterangan, ArrayList<String>tgl,
//                ArrayList<String>status);
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(DetailCashFlow.this));
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailCashFlow.this, BerandaActivity.class));
            }
        });
    }
//    private void display(){
//        Cursor cursor = helper.readAllData();
//        if (cursor.getCount() == 0){
//            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
//        }else{
//            while(cursor.moveToNext()){
//                id.add(cursor.getString(0));
//                simbol.add(cursor.getString(1));
//                nominal.add(cursor.getString(2));
//                keterangan.add(cursor.getString(3));
//                tgl.add(cursor.getString(4));
//                status.add(cursor.getString(5));
//            }
//        }
}