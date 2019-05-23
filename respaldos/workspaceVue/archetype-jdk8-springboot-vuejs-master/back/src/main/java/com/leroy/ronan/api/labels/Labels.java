package com.leroy.ronan.api.labels;

public class Labels {

    private String error;
    private Login login;
    private About about;

    public Labels() {
        this.error = "Error";
        this.login = new Login();
        this.about = new About();
    }

    public String getError() {
        return error;
    }

    public Login getLogin() {
        return login;
    }

    public About getAbout() {
        return about;
    }
}
