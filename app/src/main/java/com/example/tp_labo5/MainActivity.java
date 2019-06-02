package com.example.tp_labo5;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, MyOnItemClick {

    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;

    MyAdapter adapter;
    List<Noticia> listNoticias;
    MyOnItemClick myOnItemClick;
    Handler handler;
    String titulo;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNoticias = new ArrayList<>();
        handler = new Handler(this);

        RecyclerView rvNoticias = (RecyclerView) findViewById(R.id.recy_noticias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNoticias.setLayoutManager(layoutManager);

        adapter = new MyAdapter(listNoticias, this,this.handler);
        rvNoticias.setAdapter(adapter);

        this.actionBar = getSupportActionBar();

        actionBar.setTitle("Bienvenido a NotiTecno...");
        actionBar.setSubtitle("Elija su pagina de noticias --->");

        //actionBar.setDisplayHomeAsUpEnabled(true);



        //lo ideal es crear los hilos e iniciarlos en onStart() y luego hay que detenerlos en onStop()
            }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg2 == TEXTO) {
            this.listNoticias = this.adapter.setListNoticias((List<Noticia>) msg.obj);
            adapter.notifyDataSetChanged();
        }
        else if (msg.arg2 == IMAGEN) {
                this.adapter.setImagen((byte[]) msg.obj, msg.arg1);
                adapter.notifyItemChanged(msg.arg1);
        }
        return false;
    }

    @Override
    public void onItemClick(View v, int position) {
        this.listNoticias.get(position).getUrlImagen();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

       /* MenuItem mi = menu.findItem(R.id.txtBuscar);
        Ser sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(this); */

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String pagina="";
        if (item.getItemId()== R.id.txtPofesional){
            pagina = "https://www.iprofesional.com/rss/tecnologia";
        }
        else if(item.getItemId()== R.id.txtClarin){
            pagina = "https://www.clarin.com/rss/tecnologia/";
        }
        else if(item.getItemId()== R.id.txtPerfil){
            pagina = "https://www.perfil.com/rss/tecnologia";
        }
        this.titulo = (String) item.getTitle();
        actionBar.setTitle("Tecno  " + this.titulo);
        actionBar.setSubtitle("");
        MyThread myThread = new MyThread(this.handler,pagina, "XML", 0);
        myThread.start();
        return super.onOptionsItemSelected(item);
    }
}
