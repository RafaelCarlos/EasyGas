package com.codigo.rafael.easygas.entities;

import java.io.Serializable;

/**
 * Created by Rafael Carlos Oliveira on 18/10/2017.
 */

public class Produto implements Serializable {

    private String nome;
    private String descricao;
    private String valor;
    private int quantidade;
    private int seta;

    public Produto() {
    }

    public Produto(String nome, String descricao, String valor, int seta) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.seta = seta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getSeta() {
        return seta;
    }

    public void setSeta(int seta) {
        this.seta = seta;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor='" + valor + '\'' +
                ", quantidade=" + quantidade +
                ", seta=" + seta +
                '}';
    }
}
