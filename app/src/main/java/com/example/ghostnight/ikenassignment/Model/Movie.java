package com.example.ghostnight.ikenassignment.Model;

public class Movie {

    private int id;
    private float rating;
    private float popularity;
    private String language;
    private boolean adult;
    private String title;
    private String image;
    private String overview;
    private String release_date;
    private int vote;

    public Movie(int id, float rating, float popularity, String language, boolean adult, String title, String image, String overview, String release_date, int vote) {
        this.id = id;
        this.rating = rating;
        this.popularity = popularity;
        this.language = language;
        this.adult = adult;
        this.title = title;
        this.image = image;
        this.overview = overview;
        this.release_date = release_date;
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
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

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}