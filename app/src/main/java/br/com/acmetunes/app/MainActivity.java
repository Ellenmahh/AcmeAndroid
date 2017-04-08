package br.com.acmetunes.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {
    public static Context context;
    ListView list_view;
    AcmeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        list_view = (ListView) findViewById(R.id.list_view);


        new ObterDadosAPi().execute();
    }



    private class ObterDadosAPi extends AsyncTask<Void, Void, Void> {
        Filmes[] lstFilmes;
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            /*progressDialog = ProgressDialog.show(
                    MainActivity.context,
                    "Carregando",
                    "Aguarde", true);*/
        }

        @Override
        protected Void doInBackground(Void... params) {
            String server =  MainActivity.context.getString(R.string.endServidor)+ "api.php";

            Log.d("ObterDadosAPi",server);
            String json = Http.get(server );

            Log.d("ObterDadosAPi",json);

            Gson gson = new Gson();
            lstFilmes = gson.fromJson(json, Filmes[].class);

            for(Filmes f : lstFilmes)
               Log.d("ObterDadosAPi",f.getNome()+"\n");


            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //adapter.clear();//limpar o adapter
            //adapter.addAll(lstFilmes); // preenche-lo com dados novos

           // progressDialog.dismiss();

            AcmeAdapter adapter = new AcmeAdapter(
            MainActivity.context,
           R.layout.list_view_item,
               lstFilmes);

            list_view.setAdapter(adapter);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
