package co.cdmunoz.themoviedb.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import co.cdmunoz.themoviedb.MoviesDBApplication;
import co.cdmunoz.themoviedb.data.MovieItem;
import co.cdmunoz.themoviedb.data.MoviesResponse;
import co.cdmunoz.themoviedb.data.source.local.MoviesDBDao;
import co.cdmunoz.themoviedb.data.source.local.MoviesDBDatabase;
import co.cdmunoz.themoviedb.data.source.remote.MoviesDBService;
import co.cdmunoz.themoviedb.utils.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDBViewModel extends ViewModel {

    private static final String TAG = MoviesDBViewModel.class.getName();

    private MoviesDBDatabase database = MoviesDBDatabase.getMoviesDbDatabase(MoviesDBApplication.instance);
    private Call<MoviesResponse> call;

    public LiveData<List<MovieItem>> getMovies() {
        return database.moviesDBDao().queryMovies();
    }

    public void loadMovies() {
        boolean isConnected = Utilities.isConnectionAvailable(MoviesDBApplication.instance);
        boolean hasApiData = Utilities.shouldGetDataFromApi(MoviesDBApplication.instance);
        if (isConnected && hasApiData) {
            loadMoviesFromApi();
            Utilities.putLongSharedPreferences(MoviesDBApplication.instance, "pref_time_net_query",
                    Calendar.getInstance().getTimeInMillis());
        }
    }

    private void loadMoviesFromApi() {
        call = MoviesDBService.INSTANCE.getMovies("2019", "df1b9abfde892d0d5407d6b602b349f2");
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                MoviesResponse moviesResponse = response.body();
                if (null != moviesResponse) {
                    if (null != moviesResponse.getResults()) {
                        int numberOfItems = moviesResponse.getResults().size();
                        Log.i(TAG, "+++++++++++++++++ Size of results: " + numberOfItems);
                    }
                    List<MovieItem> movies = moviesResponse.getResults();
                    if (null != movies) {
                        for (MovieItem movieItem : movies) {
                            new InsertMovieItemAsyncTask(database.moviesDBDao()).execute(movieItem);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, "Error getting results from API: " + t.getMessage());
            }
        });
    }

    private static class InsertMovieItemAsyncTask extends AsyncTask<MovieItem, Void, Void> {

        private MoviesDBDao moviesDBDao;

        InsertMovieItemAsyncTask(MoviesDBDao moviesDBDao) {
            this.moviesDBDao = moviesDBDao;
        }

        @Override
        protected Void doInBackground(MovieItem... movieItems) {
            moviesDBDao.insertMovie(movieItems[0]);
            return null;
        }
    }
}

