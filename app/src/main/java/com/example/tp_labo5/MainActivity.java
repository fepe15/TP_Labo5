package com.example.tp_labo5;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, MyOnItemClick {

    MyAdapter adapter;
    List<Noticia> listNoticias;
    MyOnItemClick myOnItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNoticias = new ArrayList<>();
        Handler handler = new Handler(this);

        RecyclerView rvNoticias = (RecyclerView) findViewById(R.id.recy_noticias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNoticias.setLayoutManager(layoutManager);

        adapter = new MyAdapter(listNoticias, this);
        rvNoticias.setAdapter(adapter);

        MyThread myThread = new MyThread(handler,"http://www.ambito.com/rss/deportes.xml", "XML");
        myThread.start();


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
}
