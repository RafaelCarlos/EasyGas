package com.codigo.rafael.easygas.entities;

import java.io.Serializable;

/**
 * Created by Rafael Carlos Oliveira on 20/10/2017.
 */

public class Cartao implements Serializable {

    private int bandeiraCartao;
    private String titularCartao;
    private String numeroCartao;

    public Cartao() {
    }

    public Cartao(int bandeiraCartao, String titularCartao, String numeroCartao) {
        this.bandeiraCartao = bandeiraCartao;
        this.titularCartao = titularCartao;
        this.numeroCartao = numeroCartao;
    }

    public int getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(int bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public String getTitularCartao() {
        return titularCartao;
    }

    public void setTitularCartao(String titularCartao) {
        this.titularCartao = titularCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "bandeiraCartao=" + bandeiraCartao +
                ", titularCartao='" + titularCartao + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                '}';
    }
}
