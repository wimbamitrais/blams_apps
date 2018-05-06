package com.app.wimba.blams.model;

import java.util.Date;
import java.util.List;

/**
 * Created by IT02107 on 1/14/2018.
 */

public abstract class Content {

    private Long id;
    private String title;
    private Account account;
    private String location;
    private Date createdTime;
    private List <Integer> images;
    private String description;
    private Integer likes;
    private Integer comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getImagesSize(){
        return this.images.size();
    }

    public List<Integer> getListImage() {
        return images;
    }

    public void setListImage(List<Integer> listImage) {
        this.images = listImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
