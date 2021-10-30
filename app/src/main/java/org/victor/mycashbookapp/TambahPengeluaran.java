package org.victor.mycashbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.victor.mycashbookapp.Helper.formatNumber;
import org.victor.mycashbookapp.Helper.helperr;

import java.util.Calendar;

public class TambahPengeluaran extends AppCompatActivity {
    private EditText tanggal,nominal,keterangan;
    private Button btnSave, btnBack;
    private helperr helper;
    private ImageButton imgDate;
    private int tahun,bulan,hari;
    static final int DATE_PICKER_ID = 1111;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengeluaran);

        tanggal =findViewById(R.id.inputTgl);
        nominal = findViewById(R.id.inputUang);
        keterangan = findViewById(R.id.inputKet);
        btnSave = findViewById(R.id.buttonSImpan);
        btnBack = findViewById(R.id.buttonKembali);
        imgDate = findViewById(R.id.btnDateOut);
        final Calendar c =Calendar.getInstance();
        hari = c.get(Calendar.DAY_OF_MONTH);
        bulan = c.get(Calendar.MONTH);
        tahun = c.get(Calendar.YEAR);
        helper =new helperr(this);

        nominal.addTextChangedListener(new formatNumber(nominal));

        tanggal.setEnabled(false);
        tanggal.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(hari).append("-").append(bulan + 1).append("-")
                .append(tahun).append(" "));
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getDate = tanggal.getText().toString();
                String getNominalstr =formatNumber.parseCurrencyValue(nominal.getText().toString()).toString();
                String getKeterangan =keterangan.getText().toString();
                String simbol = "[ - ]";
                String status = "keluar";

                if(TextUtils.isEmpty(getDate) || TextUtils.isEmpty(getNominalstr) || TextUtils.isEmpty(getKeterangan)){
                    Toast.makeText(TambahPengeluaran.this,"Data Tidak Lengkap!", Toast.LENGTH_LONG).show();
                }else{
                    helper.insertNominal(simbol,getDate, Integer.valueOf(getNominalstr), getKeterangan, status.trim());
                    intent = new Intent(TambahPengeluaran.this,BerandaActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Berhasil Menambahkan",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TambahPengeluaran.this, BerandaActivity.class));
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, tahun, bulan,hari);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            tahun  = selectedYear;
            bulan = selectedMonth;
            hari   = selectedDay;
            // Show selected date
            tanggal.setText(new StringBuilder().append(hari)
                    .append("-").append(bulan + 1).append("-").append(tahun)
                    .append(" "));
        }
    };
}