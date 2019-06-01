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

    MyAdapter adapter;
    List<Noticia> listNoticias;
    MyOnItemClick myOnItemClick;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNoticias = new ArrayList<>();
        handler = new Handler(this);

        RecyclerView rvNoticias = (RecyclerView) findViewById(R.id.recy_noticias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNoticias.setLayoutManager(layoutManager);

        adapter = new MyAdapter(listNoticias, this);
        rvNoticias.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("NotiTecno");
        //actionBar.setDisplayHomeAsUpEnabled(true);



        //lo ideal es crear los hilos e iniciarlos en onStart() y luego hay que detenerlos en onStop()
            }

    @Override
    public boolean handleMessage(Message msg) {


        this.listNoticias = this.adapter.setListNoticias((List<Noticia>)msg.obj);
        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onItemClick(View v, int position) {

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
        MyThread myThread = new MyThread(this.handler,pagina, "XML");
        myThread.start();
        return super.onOptionsItemSelected(item);
    }
}
