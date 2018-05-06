package com.app.wimba.blams.model;

import java.util.Date;

/**
 * Created by IT02107 on 1/14/2018.
 */

public class Like {
    private Account account;
    private Date likeCreated;

    public Like(Account account) {
        this.account = account;
        this.likeCreated = new Date();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getLikeCreated() {
        return likeCreated;
    }

    public void setLikeCreated(Date likeCreated) {
        this.likeCreated = likeCreated;
    }
}
