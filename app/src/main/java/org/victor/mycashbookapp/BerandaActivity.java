package org.victor.mycashbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.victor.mycashbookapp.Helper.helperr;

import java.text.NumberFormat;
import java.util.Locale;

public class BerandaActivity extends AppCompatActivity {
    private ImageButton btnPemasukan, btnPengeluaran,btnCashflow,btnSetting;
    private TextView pemasukan,pengeluaran;
    private helperr helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        helper = new helperr(this);
        pemasukan = findViewById(R.id.pemasukan);
        pengeluaran = findViewById(R.id.pengeluaran);

        Integer countCashIn = helper.countDataPengeluaran();
        Integer countCashOut = helper.countDataPemasukan();
        pemasukan.setText("Pemasukan: " + formatRupiah(Double.parseDouble(countCashIn.toString())));
        pengeluaran.setText("Pengeluaran: " + formatRupiah(Double.parseDouble(countCashOut.toString())));
        btnPemasukan = findViewById(R.id.tmbhPemasukan);
        btnPengeluaran = findViewById(R.id.tmbhPengeluaran);
        btnCashflow = findViewById(R.id.btnDetail);
        btnSetting = findViewById(R.id.btnPengaturan);

        btnPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerandaActivity.this, TambahPemasukan.class));
            }
        });
        btnPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerandaActivity.this,TambahPengeluaran.class));
            }
        });
        btnCashflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerandaActivity.this, DetailCashFlow.class));
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BerandaActivity.this,Setting.class));
            }
        });
    }
    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}