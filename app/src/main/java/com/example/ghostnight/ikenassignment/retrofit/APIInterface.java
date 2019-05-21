package com.example.ghostnight.ikenassignment.retrofit;


import com.example.ghostnight.ikenassignment.retrofit.retrofit_model.SearchMoviesPageResbonseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("search/movie")
    Call<SearchMoviesPageResbonseModel> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page);
}
