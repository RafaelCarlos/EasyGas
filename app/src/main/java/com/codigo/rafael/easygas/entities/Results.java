package com.codigo.rafael.easygas.entities;

/**
 * Created by shang on 10/10/2017.
 */

public class Results {

    private String formatedAddress;

    public Results() {
    }

    public Results(String formatedAddress) {
        this.formatedAddress = formatedAddress;
    }

    public String getFormatedAddress() {
        return formatedAddress;
    }

    public void setFormatedAddress(String formatedAddress) {
        this.formatedAddress = formatedAddress;
    }

    @Override
    public String toString() {
        return "Results{" +
                "formatedAddress='" + formatedAddress + '\'' +
                '}';
    }
}
