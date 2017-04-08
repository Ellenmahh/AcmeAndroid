package br.com.acmetunes.app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    EditText txt_userName, txt_password;
    Button btnLogin,btn_cadastro;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = this;

        txt_userName =  (EditText) findViewById(R.id.txt_userName);
        txt_password =  (EditText) findViewById(R.id.txt_password);
        btnLogin=  (Button) findViewById(R.id.btn_login);
        btn_cadastro=(Button) findViewById(R.id.btn_cadastro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });

        btn_cadastro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                cadastro();
            }
        });
    }

    public void login(){

        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();
        Cursor cursor =  db.rawQuery(
                "SELECT * FROM tbl_user WHERE login = ? and senha = ?;",
                new String[]{
                        txt_userName.getText().toString(),
                        txt_password.getText().toString()
                }
        );

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            Sessao.userName = cursor.getString(1);


            Log.d("Login", Sessao.userName);


            //Abrir tela principal"
            startActivity(
                    new Intent(context, MainActivity.class));
        }else{

            Toast.makeText(context, "Login invalido", Toast.LENGTH_SHORT).show();
        }


    }
    public void cadastro(){


        Toast.makeText(context,"ahaaaaaaaa",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context,CadastroActivity.class));


    }




}

