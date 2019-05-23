package com.example.ghostnight.ikenassignment.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghostnight.ikenassignment.Model.Movie;
import com.example.ghostnight.ikenassignment.R;
import com.example.ghostnight.ikenassignment.retrofit.NetworkService;
import com.example.ghostnight.ikenassignment.retrofit.retrofit_model.SearchMoviesPageResbonseModel;
import com.example.ghostnight.ikenassignment.retrofit.retrofit_response.SearchMoviesResbonse;
import com.example.ghostnight.ikenassignment.ui.adapter.MovieListAdapter;
import com.example.ghostnight.ikenassignment.ui.adapter.QueryAdapter;
import com.example.ghostnight.ikenassignment.utils.NetworkUtils;

import java.util.ArrayList;


public class MainFragment extends Fragment implements MovieListAdapter.LoadMoreListener, SearchMoviesResbonse.MoviesResbonseListener, QueryAdapter.QueryItemClickListener {

    private OnFragmentInteractionListener mListener;
    private RecyclerView list;
    private MovieListAdapter adapter;
    private QueryAdapter queryAdapter;
    private ArrayList<Movie> mMovies;
    private EditText queryEdit;
    private Button clearBtn;
    private TextView noItem;
    private int pageNumber;
    private String query;
    private ProgressBar loader;
    ArrayList<String> queries;

    public interface OnFragmentInteractionListener {
        void onListItemSelected(Movie movie);
    }

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        pageNumber=1;
        mMovies = new ArrayList<>();
        queries = new ArrayList<>();
        loader = view.findViewById(R.id.progress);
        noItem = view.findViewById(R.id.noItems);
        clearBtn = view.findViewById(R.id.clear_btn);
        queryEdit = view.findViewById(R.id.query_editText);
        LinearLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        list = view.findViewById(R.id.list);
        list.setLayoutManager(gridLayoutManager);
        list.setHasFixedSize(true);
        queryAdapter = new QueryAdapter(queries, this, getContext());


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryEdit.getText().clear();
                clearBtn.setVisibility(View.GONE);
            }
        });

        queryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setAdapter(queryAdapter);
            }
        });

        queryEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == ""){
                    clearBtn.setVisibility(View.GONE);
                }else{
                    clearBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")){
                    clearBtn.setVisibility(View.GONE);
                }
            }
        });

        queryEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    queryEdit.clearFocus();
                    list.setAdapter(adapter);
                    pageNumber = 1;
                    query = queryEdit.getText().toString();
                    callApi(pageNumber, query);
                    return true;
                }
                return false;
            }
        });



        return view;
    }

    private void callApi(int pageNumber, String query) {
        showLoader();
        if (NetworkUtils.isNetworkConnected(getContext())) {
            NetworkService.getInstance().searchMovie(pageNumber, query, MainFragment.this);
        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            populateList();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLoadMoreClick() {
        mMovies.add(null);
        adapter.notifyItemInserted(mMovies.size()-1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMovies.remove(mMovies.size()-1);
                adapter.notifyItemRemoved(mMovies.size());
                pageNumber++;
                callApi(pageNumber, query);
            }
        }, 500);
    }

    @Override
    public void onListItemClick(Movie movie) {
        mListener.onListItemSelected(movie);
    }

    @Override
    public void onGetMoviesSuccessfuly(SearchMoviesPageResbonseModel body) {
        if(pageNumber == 1) {
            if(!(queries.indexOf(query) >=0)) {
                if (queries.size() < 10) {
                    queries.add(0, query);
                } else {
                    queries.remove(9);
                    queries.add(0, query);
                }
                queryAdapter = new QueryAdapter(queries, this, getContext());
            }

            mMovies.clear();
            for (int i = 0; i < body.getResults().size(); i++) {
                mMovies.add(new Movie(body.getResults().get(i).getId(),
                        body.getResults().get(i).getVote_average(),
                        body.getResults().get(i).getPopularity(),
                        body.getResults().get(i).getOriginal_language(),
                        body.getResults().get(i).isAdult(),
                        body.getResults().get(i).getTitle(),
                        body.getResults().get(i).getPoster_path(),
                        body.getResults().get(i).getOverview(),
                        body.getResults().get(i).getRelease_date(),
                        body.getResults().get(i).getVote_count()));

            }
            populateList();
        }else{
            for (int i = 0; i < body.getResults().size(); i++) {
                mMovies.add(new Movie(body.getResults().get(i).getId(),
                        body.getResults().get(i).getVote_average(),
                        body.getResults().get(i).getPopularity(),
                        body.getResults().get(i).getOriginal_language(),
                        body.getResults().get(i).isAdult(),
                        body.getResults().get(i).getTitle(),
                        body.getResults().get(i).getPoster_path(),
                        body.getResults().get(i).getOverview(),
                        body.getResults().get(i).getRelease_date(),
                        body.getResults().get(i).getVote_count()));
                adapter.notifyItemInserted(mMovies.size());
            }
            adapter.setLoaded();
            list.setVisibility(View.VISIBLE);
            noItem.setVisibility(View.GONE);
            hideLoader();
        }
    }

    @Override
    public void onGetMoviesFailed(String status_message) {
        Toast.makeText(getContext(), status_message, Toast.LENGTH_LONG).show();
        populateList();
    }

    public void populateList() {
        if (mMovies.size() > 0) {
            adapter = new MovieListAdapter(mMovies, this, getContext(), list);
            list.setAdapter(adapter);
            list.setVisibility(View.VISIBLE);
            noItem.setVisibility(View.GONE);
        } else if (mMovies.size() == 0 && !NetworkUtils.isNetworkConnected(getContext())) {
            list.setVisibility(View.GONE);
            noItem.setText(getString(R.string.no_internet));
            noItem.setVisibility(View.VISIBLE);
        } else {
            list.setVisibility(View.GONE);
            noItem.setText(getString(R.string.no_movies));
            noItem.setVisibility(View.VISIBLE);
        }
        hideLoader();
    }

    void showLoader() {
        loader.setVisibility(View.VISIBLE);
        list.setVisibility(View.INVISIBLE);
    }

    void hideLoader() {
        loader.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
    }

    @Override
    public void onQueryItemClick(String query) {
        pageNumber=1;
        this.query=query;
        callApi(pageNumber, query);
    }
}
