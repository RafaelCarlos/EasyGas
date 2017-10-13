package com.codigo.rafael.easygas.entities;

import android.widget.Button;

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
    private int btCar;

    public Menu() {
    }

    public Menu(String titulo, String bairro, double distancia, int foto, String valor, int avaliacao, int btCar) {
        this.titulo = titulo;
        this.bairro = bairro;
        this.distancia = distancia;
        this.foto = foto;
        this.valor = valor;
        this.avaliacao = avaliacao;
        this.btCar = btCar;
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

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getBtCar() {
        return btCar;
    }

    public void setBtCar(int btCar) {
        this.btCar = btCar;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "titulo='" + titulo + '\'' +
                ", bairro='" + bairro + '\'' +
                ", distancia=" + distancia +
                ", foto=" + foto +
                ", valor='" + valor + '\'' +
                ", avaliacao=" + avaliacao +
                ", btCar=" + btCar +
                '}';
    }
}
