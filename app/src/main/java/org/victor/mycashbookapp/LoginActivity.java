package org.victor.mycashbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.victor.mycashbookapp.Helper.helperr;

public class LoginActivity extends AppCompatActivity {
    EditText inputUsername, inputPassword, Email, Password;
    Button buttonLogin;
    helperr helperr;
    Boolean EditTextEmptyHolder;
    String UsernameHolder, PasswordHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String TempPassword = "NOT FOUND";
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText)findViewById(R.id.inputPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        helperr = new helperr(this);

        buttonLogin.setOnClickListener(v -> {
            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            Boolean res  = helperr.checkUser(username,password);
            if (res == true){
                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,BerandaActivity.class));
            }else{
                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
            }
//            checkStatus();
//            login();
        });
    }

    @SuppressLint("Range")
    private void login() {
        if(EditTextEmptyHolder) {
            sqLiteDatabaseObj = helperr.getWritableDatabase();
            cursor = sqLiteDatabaseObj.query(org.victor.mycashbookapp.Helper.helperr.tabel_user, null, " " + org.victor.mycashbookapp.Helper.helperr.row_username + "=?", new String[]{UsernameHolder}, null, null, null);

            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    TempPassword = cursor.getString(cursor.getColumnIndex(org.victor.mycashbookapp.Helper.helperr.row_password));
                    cursor.close();
                }
            }
            CheckFinalResult();
        }
        else {
            Toast.makeText(LoginActivity.this,"Silakan Masukkan Username atau Password.", Toast.LENGTH_LONG).show();
        }
    }

    private void CheckFinalResult() {
        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {
            Toast.makeText(LoginActivity.this,"Login Berhasil",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this,"Username dan Password Salah, Silakan Coba Lagi.",Toast.LENGTH_LONG).show();
        }
        TempPassword = "NOT_FOUND" ;
    }

    private void checkStatus() {
        UsernameHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        EditTextEmptyHolder = !TextUtils.isEmpty(UsernameHolder) && !TextUtils.isEmpty(PasswordHolder);
    }
}