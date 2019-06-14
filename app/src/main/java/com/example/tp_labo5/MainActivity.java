package com.example.tp_labo5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, MyOnItemClick, SearchView.OnQueryTextListener {

    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;

    MyAdapter adapter;
    List<Noticia> listNoticias;
    MyOnItemClick myOnItemClick;
    Handler handler;
    String titulo;
    ActionBar actionBar;
    List<String> paginas;

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

        actionBar.setTitle("........NotiTecno........");

        SharedPreferences sharedPreferences = getSharedPreferences("SharePreference", Context.MODE_PRIVATE);
        boolean yaInicio = sharedPreferences.getBoolean("Inicio",false);
        boolean checkIprofesional = sharedPreferences.getBoolean("checkIprofesional",false);
        boolean checkClarin = sharedPreferences.getBoolean("checkClarin",false);
        boolean checkPerfil = sharedPreferences.getBoolean("checkPerfil",false);

        if(!yaInicio) {

            MyDialog miDialog = new MyDialog();
            miDialog.myHandler = handler;
            miDialog.show(getSupportFragmentManager(), "manager");
        }
        else if(yaInicio){
            paginas = new ArrayList<>();
            if (checkIprofesional){
                paginas.add("https://www.iprofesional.com/rss/tecnologia");
            }
            if (checkClarin){
                paginas.add("https://www.clarin.com/rss/tecnologia/");
            }
            if (checkPerfil){
                paginas.add("https://www.perfil.com/rss/tecnologia");
            }
            MyDialog.chequeosPaginas(checkIprofesional,checkClarin,checkPerfil);
            runThreadPages();
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg2 == TEXTO) {
          //  this.formatear(this.listNoticias);
            this.adapter.setListNoticias((List<Noticia>) msg.obj);
            this.listNoticias = this.adapter.getListNoticias();
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
        Intent i = new Intent(this, WebActivity.class);
        i.putExtra("page",this.listNoticias.get(position).getLinkNoticia());
        this.startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem mi = menu.findItem(R.id.txtBuscar);
        SearchView sv = (SearchView) mi.getActionView();
        sv.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.adapter.clearLista();
        MyDialog miDialog = new MyDialog();
        miDialog.myHandler = handler;
        miDialog.show(getSupportFragmentManager(), "manager" );
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.d("TextSubmit", s);
        adapter.search(s);
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        this.adapter.search(s);
        Log.d("TextChange", s);
        return true;
    }

    //corro los hilos de todas las paginas que estan chequeadas
    public void runThreadPages() {
        if (paginas.size() > 0) {
            for (String pagina : paginas) {
                MyThread myThread = new MyThread(handler, pagina, "XML", 0);
                myThread.start();
            }
        }
    }

}
