package com.example.tp_labo5;

import java.util.Date;

public class Noticia {

    private String titulo;
    private String descripcion;
    private String urlImagen;
    private String linkNoticia;
    private String fecha;
    private String fuente;
    private byte[] fileImagen;
    private boolean cargoImagen;

    public Noticia(String titulo, String descripcion, String urlImagen){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.cargoImagen = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Boolean getCargoImagen() { return cargoImagen; }

    public void setCargoImagen(Boolean cargo) { this.cargoImagen = cargo;  }

    public byte[] getFileImagen() {
        return fileImagen;
    }

    public void setFileImagen(byte[] fileImagen) {
        this.fileImagen = fileImagen;
    }

    public String getLinkNoticia() { return linkNoticia; }

    public void setLinkNoticia(String linkNoticia) {this.linkNoticia = linkNoticia;}

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) {this.fecha = fecha;}

    public String getFuente() {return fuente;}

    public void setFuente(String fuente) {this.fuente = fuente;}

    public Noticia(){ this.cargoImagen = false;}


    @Override
    public String toString() {
        return "Este es el titulo: " + this.titulo +
                " Esta es la descri: " +  this.descripcion +
                "Este es el link de la noti: " + this.linkNoticia +
                "Esta es la url: " + this.urlImagen;
    }


}
