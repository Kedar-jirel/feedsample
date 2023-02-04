package com.example.myfeedapplication.application.models.remote;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Creater implements Serializable {

    @SerializedName("avatar")
    public String avatar;


    @SerializedName("firstName")
    public String firstName;


    @SerializedName("lastName")
    public String lastName;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
