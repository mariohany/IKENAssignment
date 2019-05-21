package com.example.ghostnight.ikenassignment.retrofit;

import com.example.ghostnight.ikenassignment.retrofit.retrofit_model.SearchMoviesPageResbonseModel;
import com.example.ghostnight.ikenassignment.retrofit.retrofit_response.SearchMoviesResbonse;

import retrofit2.Call;

public class NetworkService {

    private static final String API_KEY = "b3070a5d3abfb7c241d2688d066914e7";

    private static NetworkService INSTANCE = null;

    private NetworkService(){}

    public static NetworkService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new NetworkService();
        return INSTANCE;
    }

    public void searchMovie(int page, String query, SearchMoviesResbonse.MoviesResbonseListener listener){
        RestClient restClient = new RestClient();
        APIInterface apiInterface = restClient.createService();
        Call<SearchMoviesPageResbonseModel> call = apiInterface.searchMovie(API_KEY, query, page);
        call.enqueue(new SearchMoviesResbonse(listener));
    }
}
