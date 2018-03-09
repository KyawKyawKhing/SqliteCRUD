package com.nw.kcube.sqlitdbcrud.model;

/**
 * Created by K Cube on 2/17/2018.
 */

public class movie {
    private String  id;
    private String name;
    private String type;
    private String cinema;
    private String date;
    private String status;
    private String review;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCinema() {
        return cinema;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getReview() {
        return review;
    }

    public String getId() {
        return id;
    }

    public movie(String id, String name, String type, String cinema, String date, String status, String review) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cinema = cinema;
        this.date = date;
        this.status = status;
        this.review = review;
    }
}
