package org.victor.mycashbookapp.Helper;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class helperr extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME  = "bnsp";
    public static final String tabel_user = "user";
    public static final String row_id ="id";
    public static String row_password="password";
    public static String row_username="username";

    public static final String tabel_cash = "cashflow";
    public static final String row_simbol = "simbol";
    public static final String row_tgl = "tgl";
    public static final String row_nominal = "nominal";
    public static final String row_ket = "keterangan";
    public static final String row_stat = "status";
    private  SQLiteDatabase db;
    public helperr(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tabel_user+" ("+row_id+" VARCHAR PRIMARY KEY, "
                +row_username+" VARCHAR, "+row_password+" VARCHAR);");
        db.execSQL("INSERT INTO "+tabel_user+" ("+row_id+", "+ row_username+", "+row_password+") VALUES ('1','user', 'user');");
        db.execSQL("CREATE TABLE " + tabel_cash + " (" + row_id + " INTEGER PRIMARY KEY, " + row_simbol +
                " VARCHAR, " + row_tgl + " VARCHAR, " + row_nominal + " INTEGER, " + row_ket + " VARCHAR, " + row_stat + " VARCHAR);");
        db.execSQL("INSERT INTO "+tabel_cash+" ("+row_id+", "+row_simbol+", "+row_tgl+", "+row_nominal+", "+row_ket+", "+row_stat+") VALUES " +
                "('1','[ + ]','31-10-2021',30000,'tes msk','masuk')," +
                "('2','[ - ]','27-10-2021',50000,'tes keluar','keluar');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabel_user );
        db.execSQL("DROP TABLE IF EXISTS " + tabel_cash );
        onCreate(db);
    }
    public void insertData(ContentValues values){
        db.insert(tabel_user,null,values);
    }
    public boolean checkUser(String username, String password){
        String[] columns = {row_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_username + "=?" + " and " + row_password + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(tabel_user, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count>0){
            return true;
        }else
            return false;
    }
    public void insertNominal(String simbol, String tgl, Integer nominal, String ket, String stat){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(row_simbol, simbol);
        cValues.put(row_tgl,tgl);
        cValues.put(row_nominal,nominal);
        cValues.put(row_ket, ket);
        cValues.put(row_stat, stat);

        long newRowId = db.insert(tabel_cash,null, cValues);
        db.close();
    }
    public Cursor readAllData(){
        try {
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT * FROM " + tabel_cash + ";";
            Cursor cursor = db.rawQuery(strSQL, null);

            return cursor;

        } catch (Exception e) {
            return null;
        }
    }
    public int countDataPemasukan() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + row_nominal + ") as Total FROM " + tabel_cash + " Where "+row_stat+" = 'masuk'", null);

        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    public int countDataPengeluaran() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + row_nominal + ") as Total FROM " + tabel_cash + " Where "+row_stat+" = 'keluar'", null);

        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }
    public boolean updatePassword(user userdetail){
        boolean hasil = true;
        try {
            ContentValues contentValues =new ContentValues();
            contentValues.put(row_username, userdetail.getUsername());
            contentValues.put(row_password,userdetail.getPassword());
            hasil =db.update(tabel_user,contentValues,row_id + "=?", new String[] {String.valueOf(userdetail.getId())}) >0;
        }catch(Exception e){
            hasil =false;
        }
        return hasil;
    }
    public user findUser(int id){
        user account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tabel_user + " where id = ? ", new String[] {String.valueOf(id)});
            if (cursor.moveToFirst()){
                account = new user();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public Boolean updatePsw(int id, String oldpassword, String newpassword){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCount= db.rawQuery("select count(*) from "+tabel_user+" where "+row_password+"='" + oldpassword + "'", null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        System.out.println("old: "+oldpassword);
        System.out.println("new: "+newpassword);
        if(count > 0){
            ContentValues cv = new ContentValues();
            cv.put(row_password,newpassword);
            db.update(tabel_user, cv, null,null);
            return true;
        }
        return false;
    }
}
