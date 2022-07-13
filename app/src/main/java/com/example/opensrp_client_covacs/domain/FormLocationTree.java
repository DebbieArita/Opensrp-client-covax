package com.example.opensrp_client_covacs.domain;

import org.smartregister.domain.form.FormLocation;

import java.util.List;

public class FormLocationTree {
    private String formLocationString;
    private List<FormLocation> formLocations;

    public FormLocationTree(String formLocationString, List<FormLocation> formLocations) {
        this.formLocationString = formLocationString;
        this.formLocations = formLocations;
    }

    public String getFormLocationString() {
        return formLocationString;
    }

    public List<FormLocation> getFormLocations() {
        return formLocations;
    }
}
