package com.codigo.rafael.easygas.entities.convertersendere;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shang on 11/10/2017.
 */

public class Pojo implements Serializable {

    private List<Results> results;
    private String status;


    public Pojo() {
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "results=" + results +
                ", status='" + status + '\'' +
                '}';
    }
}
