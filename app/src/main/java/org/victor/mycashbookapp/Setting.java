package org.victor.mycashbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.victor.mycashbookapp.Helper.helperr;
import org.victor.mycashbookapp.Helper.user;

import java.util.Set;

public class Setting extends AppCompatActivity {
    private EditText inputoldpwd, inputnewpwd;
    private Button save,back;
    private helperr help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        inputoldpwd = findViewById(R.id.inputGantipswdlama);
        inputnewpwd = findViewById(R.id.inputGantipswdbaru);
        save = findViewById(R.id.btnsimpan);
        back = findViewById(R.id.btnkembali);
        help = new helperr(this);
//        Intent intent = getIntent();
//        user user = (user) intent.getSerializableExtra("account");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                user currentUser = help.findUser(user.getId());
//                if (inputoldpwd.getText().toString().equals(user.getPassword())){
//                    if (inputnewpwd.getText().toString().equals(inputoldpwd.getText().toString())){
//                        Toast.makeText(Setting.this, "The Password is Currently Used", Toast.LENGTH_SHORT).show();
//                    }else if(inputnewpwd.getText().toString().isEmpty()){
//                        Toast.makeText(Setting.this, "Fill The Password", Toast.LENGTH_SHORT).show();
//                    }else{
//                        currentUser.setPassword(inputnewpwd.getText().toString());
//                        if(help.updatePassword(currentUser)){
//                            Toast.makeText(Setting.this, "Password Has Been Changed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }else{
//                    Toast.makeText(Setting.this, "Invalid Password", Toast.LENGTH_SHORT).show();
//                }
//                System.out.println("old: " + inputoldpwd);
//                System.out.println("new: " + inputnewpwd);
                String oldpassword = inputoldpwd.getText().toString();
                String newpassword = inputnewpwd.getText().toString();

                if(TextUtils.isEmpty(oldpassword) || TextUtils.isEmpty(newpassword)){
                    Toast.makeText(Setting.this,"Silakan Masukkan Password Lama dan Password Baru.", Toast.LENGTH_LONG).show();
                }else{
                    boolean update = help.updatePsw(0, oldpassword, newpassword);
                    if (!update){
                        Toast.makeText(Setting.this,"Password Salah, Silakan Coba Lagi.",Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(Setting.this,"Berhasil Update Password.",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Setting.this, BerandaActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this, BerandaActivity.class));
            }
        });


    }
}