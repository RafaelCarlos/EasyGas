package com.codigo.rafael.easygas.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rafael Carlos Oliveira on 18/10/2017.
 */

public class Avaliacao implements Serializable {


    private String nomeUsuario;
    private String descricao;
    private float nota;
    private Date dataAvaliacao;

    public Avaliacao() {
    }

    public Avaliacao(String nomeUsuario, String descricao, float nota, Date dataAvaliacao) {
        this.nomeUsuario = nomeUsuario;
        this.descricao = descricao;
        this.nota = nota;
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "nomeUsuario='" + nomeUsuario + '\'' +
                ", descricao='" + descricao + '\'' +
                ", nota=" + nota +
                ", dataAvaliacao=" + dataAvaliacao +
                '}';
    }
}
