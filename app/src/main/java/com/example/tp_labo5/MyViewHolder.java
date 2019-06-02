package com.example.tp_labo5;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtTitulo;
    TextView txtDescripcion;
    ImageView imagen;
    private int posicion;
    private MyOnItemClick myOnItemClick;



    public MyViewHolder(@NonNull View itemView, MyOnItemClick myOnItemClick) {
        super(itemView);
        txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
        txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
        imagen = (ImageView) itemView.findViewById(R.id.imagen);
        this.myOnItemClick = myOnItemClick;
        itemView.setOnClickListener(this);
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }


    @Override
    public void onClick(View v) {
        this.myOnItemClick.onItemClick(v,posicion);
    }
}
