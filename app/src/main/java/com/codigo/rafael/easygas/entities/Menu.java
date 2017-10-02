package com.codigo.rafael.easygas.entities;

/**
 * Created by shang on 02/10/2017.
 */

public class Menu {

    private String titulo;
    private String bairro;
    private double distancia;
    private int foto;
    private String valor;
    private int avaliacao;

    public Menu() {
    }

    public Menu(String titulo, String bairro, double distancia, int foto, String valor, int avaliacao) {
        this.titulo = titulo;
        this.bairro = bairro;
        this.distancia = distancia;
        this.foto = foto;
        this.valor = valor;
        this.avaliacao = avaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "titulo='" + titulo + '\'' +
                ", bairro='" + bairro + '\'' +
                ", valor='" + valor + '\'' +
                ", foto=" + foto +
                ", avaliacao=" + avaliacao +
                ", distancia=" + distancia +
                '}';
    }
}
