package co.cdmunoz.themoviedb.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import co.cdmunoz.themoviedb.R;
import co.cdmunoz.themoviedb.data.MovieItem;

public class MainActivity extends AppCompatActivity {

    private MoviesDBViewModel moviesDBViewModel;
    private MoviesDBAdapter moviesDBAdapter;
    private Parcelable recyclerViewState;
    private RecyclerView moviesListRecyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initRecycler();
        initViewModels();
        initObservers();
        loadData();
    }

    private void initViews() {
        moviesListRecyclerView = findViewById(R.id.movies_list);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void initRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        moviesListRecyclerView.setHasFixedSize(true);
        moviesListRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initViewModels() {
        moviesDBViewModel = ViewModelProviders.of(this).get(MoviesDBViewModel.class);
    }

    private void initObservers() {
        moviesDBViewModel.getMovies().observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable List<MovieItem> movieItems) {
                if (null != movieItems) {
                    recyclerViewState = moviesListRecyclerView.getLayoutManager().onSaveInstanceState();
                    moviesDBAdapter = new MoviesDBAdapter(movieItems);
                    moviesListRecyclerView.setAdapter(moviesDBAdapter);
                    moviesListRecyclerView.setVisibility(View.VISIBLE);
                    moviesListRecyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loadData() {
        moviesDBViewModel.loadMovies();
    }
}
