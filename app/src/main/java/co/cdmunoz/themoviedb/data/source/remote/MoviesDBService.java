package co.cdmunoz.themoviedb.data.source.remote;

import com.squareup.moshi.Moshi;

import co.cdmunoz.themoviedb.BuildConfig;
import co.cdmunoz.themoviedb.data.MoviesResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public enum MoviesDBService {

    INSTANCE;
    private final MoviesDBApi movieDbApi;

    MoviesDBService() {
        Moshi moshi = new Moshi.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        movieDbApi = retrofit.create(MoviesDBApi.class);
    }

    public Call<MoviesResponse> getMovies(String movieYear, String api_key) {
        return movieDbApi.getMovies(movieYear, api_key);
    }
}

