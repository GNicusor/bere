package org.example;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Angajat {
    @JsonProperty("postul")
    String postul;
    @JsonProperty("numele")
    String numele;
    @JsonProperty("data_angajarii")
    LocalDate data_angajarii;
    @JsonProperty("salariul")
    float salariul;

    public Angajat() {}

    public Angajat(String postul, String numele, LocalDate data_angajarii, float salariul) {
        this.postul = postul;
        this.numele = numele;
        this.data_angajarii = data_angajarii;
        this.salariul = salariul;
    }

    @Override
    public String toString() {
        return numele + ",postul" + postul + "," + data_angajarii + "," + salariul;
    }

    public String getPostul() {
        return postul;
    }

    public void setPostul(String postul) {
        this.postul = postul;
    }

    public String getNumele() {
        return numele;
    }

    public void setNumele(String numele) {
        this.numele = numele;
    }

    public LocalDate getData_angajarii() {
        return data_angajarii;
    }

    public void setData_angajarii(LocalDate data_angajarii) {
        this.data_angajarii = data_angajarii;
    }

    public float getSalariul() {
        return salariul;
    }

    public void setSalariul(float salariul) {
        this.salariul = salariul;
    }
}
