package com.example.apicalling.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Response implements Serializable {
    @SerializedName("images")
    public Image[] image;
}
