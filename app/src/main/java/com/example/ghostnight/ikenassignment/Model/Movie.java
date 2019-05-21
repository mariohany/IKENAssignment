package com.example.ghostnight.ikenassignment.Model;

public class Movie {

    private String title;
    private String image;
    private String overview;
    private String release_date;

    public Movie(String title, String image, String overview, String release_date) {
        this.title = title;
        this.image = image;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return "https://image.tmdb.org/t/p/w500"+image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

}