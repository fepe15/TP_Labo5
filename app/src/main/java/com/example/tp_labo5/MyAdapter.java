package com.example.tp_labo5;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private List<Noticia> listNoticias;
    private List<Noticia> listNoticiasCompleta;
    private MyOnItemClick myOnItemClick;
    private Handler handler;

    public MyAdapter(List<Noticia> listNoticias, MyOnItemClick myOnItemClick, Handler handler){
        this.listNoticias = listNoticias;
        this.myOnItemClick = myOnItemClick;
        this.handler = handler;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int posicion) {
        View v =null;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v,myOnItemClick);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        Noticia noti = this.listNoticias.get(position);

        holder.txtTitulo.setText(noti.getTitulo());
        holder.txtDescripcion.setText(noti.getDescripcion());
        holder.txtFuente.setText(noti.getFuente());
        holder.txtFecha.setText(noti.getFecha());

         if (!noti.getCargoImagen()){
              Log.d("entrooooo", "carga segundo hilo y la imagen " + noti.getUrlImagen());
              MyThread myThreadTwo = new MyThread(handler,noti.getUrlImagen(),"IMAGEN", position);
              myThreadTwo.start();
         }
         if (noti.getFileImagen() != null) {
                 holder.imagen.setImageBitmap(BitmapFactory.decodeByteArray(noti.getFileImagen(), 0, noti.getFileImagen().length));
         }
        holder.setPosicion(position);
    }


    @Override
    public int getItemCount() {
        return this.listNoticias.size();
    }

    public void setListNoticias(List<Noticia> noticias) {
        this.listNoticias.addAll(noticias);
        this.listNoticiasCompleta = new ArrayList<Noticia>(noticias);
    }

    public List<Noticia> getListNoticias(){
        return this.listNoticias;
    }

    public void setImagen(byte[] imagen,int position){
        this.listNoticias.get(position).setFileImagen(imagen);
        this.listNoticias.get(position).setCargoImagen(true);
    }

    public void search(String text) {

        this.listNoticias.clear();
        if(text.length()<=3)
        {
            this.listNoticias.addAll(this.listNoticiasCompleta);
            Log.d("Tamaño Lista Original: " + String.valueOf(this.listNoticias.size()) , "Tamaño lista completa: "+String.valueOf(this.listNoticiasCompleta.size()));
            notifyDataSetChanged();
        }
        else {
            text = text.toLowerCase();
            for(Noticia noticia: this.listNoticiasCompleta)
            {
                if(noticia.getTitulo().toLowerCase().contains(text))
                {
                    this.listNoticias.add(noticia);
                }
            }
        }
        notifyDataSetChanged();
    }
}
