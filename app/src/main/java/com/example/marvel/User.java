package com.example.marvel;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String nickname;
    public User(){

    }
    public User(String firstname, String lastname, String email, String password,String nickname){
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=password;
        this.nickname=nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
