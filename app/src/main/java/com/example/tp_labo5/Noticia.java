package com.example.tp_labo5;

public class Noticia {

    String titulo;
    String descripcion;
    String urlImagen;
    private byte[] fileFoto;

    public Noticia(){

    }

    public Noticia(String titulo, String descripcion, String urlImagen){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
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

    @Override
    public String toString() {
        return "Este es el titulo: " + this.titulo +
                " Esta es la descri: " +  this.descripcion +
                "Esta es la url: " + this.urlImagen;
    }

    public byte[] getFileFoto() {
        return fileFoto;
    }

    public void setFileFoto(byte[] fileFoto) {
        this.fileFoto = fileFoto;
    }


}
