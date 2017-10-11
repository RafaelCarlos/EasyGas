package com.codigo.rafael.easygas.entities;

/**
 * Created by shang on 10/10/2017.
 */

public class Results {

    private String formatted_address;

    public Results() {
    }

    public Results(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    @Override
    public String toString() {
        return "Results{" +
                "formatted_address='" + formatted_address + '\'' +
                '}';
    }
}
