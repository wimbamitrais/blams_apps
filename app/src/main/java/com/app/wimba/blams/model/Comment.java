package com.app.wimba.blams.model;

import java.util.Date;

/**
 * Created by IT02107 on 1/14/2018.
 */

public class Comment {

    private Account account;
    private Date commentCreated;
    private String content;

    public Comment(Account account, String content) {
        this.account = account;
        this.commentCreated = new Date();
        this.content = content;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCommentCreated() {
        return commentCreated;
    }

    public void setCommentCreated(Date commentCreated) {
        this.commentCreated = commentCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
