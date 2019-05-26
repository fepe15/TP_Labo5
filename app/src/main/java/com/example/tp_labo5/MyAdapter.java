package com.example.tp_labo5;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private List<Noticia> listNoticias;
    private MyOnItemClick myOnItemClick;

    public MyAdapter(List<Noticia> listNoticias, MyOnItemClick myOnItemClick){
        this.listNoticias = listNoticias;
        this.myOnItemClick = myOnItemClick;
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

        holder.txtTitulo.setText(listNoticias.get(position).getTitulo());
        holder.txtDescripcion.setText(listNoticias.get(position).getDescripcion());

        holder.setPosicion(position);
    }

    @Override
    public int getItemCount() {
        return this.listNoticias.size();
    }

    public List<Noticia> setListNoticias(List<Noticia> listNoticias) {
        return this.listNoticias = listNoticias;
    }

    public List<Noticia> getListNoticias(){
        return this.listNoticias;
    }
}
