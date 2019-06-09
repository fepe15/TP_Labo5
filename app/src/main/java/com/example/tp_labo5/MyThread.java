package com.example.tp_labo5;

import android.os.Message;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;

public class MyThread extends Thread {
    private Handler handler;
    private String url;
    private int posicion;
    String tipo;

    public MyThread(Handler handler , String url, String tipo, int posicion){
        this.handler = handler;
        this.url = url;
        this.tipo = tipo;
        this.posicion = posicion;
    }

    @Override
    public void run() {
        try {
            MyConnection connection = new MyConnection("GET");
            Message msg = new Message();
            msg.arg1 = this.posicion;
            if (this.tipo.equals("XML")) {
                String resConexion = connection.getStringData(url);
                msg.obj = MyXmlParser.obtenerNoticias(resConexion);
                msg.arg2 = MainActivity.TEXTO;
                this.handler.sendMessage(msg);
            }
            if (this.tipo.equals("IMAGEN")){
       //       Log.d("Devuelve imagen", "");
                byte[] imagen = connection.getImageData(url);
                msg.obj = imagen;
                msg.arg2 = MainActivity.IMAGEN;
                this.handler.sendMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
