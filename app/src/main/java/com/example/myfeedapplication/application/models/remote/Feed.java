package com.example.myfeedapplication.application.models.remote;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public  class Feed implements Serializable {

//    {
//        "id": "1",
//            "createdAt": "2022-09-28T22:31:28.475Z",
//            "postText": "Enim debitis facilis sapiente eius. Adipisci quasi quam dolorem officiis inventore est voluptates. Commodi distinctio possimus. Cupiditate enim quia. Quis vitae numquam aut tempore.",
//            "images": [
//        "http://loremflickr.com/640/480",
//                "http://loremflickr.com/640/480"
//    ],
//        "creator": {
//        "avatar": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/245.jpg",
//                "firstName": "Gust",
//                "lastName": "Berge"
//    }
//    }

    @SerializedName("id")
    public String id;



    @SerializedName("createdAt")
    public  String createdAt;

    @SerializedName("postText")
    public  String postText;

    @SerializedName("images")
    public  ArrayList<String> images = new ArrayList<>();

    @SerializedName("creator")
    public  Creater creator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public Creater getCreator() {
        return creator;
    }

    public void setCreator(Creater creator) {
        this.creator = creator;
    }
}





