package com.leroy.ronan.api.labels;

public class Login {

    private String info;
    private String error;
    private String user;
    private String password;
    private String submit;

    public Login() {
        this.info = "Please fill up your login and password.";
        this.error = "Login and/or password are incorrect.";
        this.user = "Login";
        this.password = "Password";
        this.submit = "Submit";
    }

	public String getInfo() {
		return info;
	}

	public String getError() {
		return error;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getSubmit() {
		return submit;
	}

}
