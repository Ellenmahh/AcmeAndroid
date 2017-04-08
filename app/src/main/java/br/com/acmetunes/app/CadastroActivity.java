package br.com.acmetunes.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {
    Context context;
    EditText txt_nome_completo,txt_email,txt_senha,txt_confirmar_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        context = this;

        txt_nome_completo = (EditText) findViewById(R.id.txt_nome_completo);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_senha = (EditText) findViewById(R.id.senha);
        txt_confirmar_senha = (EditText) findViewById(R.id.confirmar_senha);

    }
}
