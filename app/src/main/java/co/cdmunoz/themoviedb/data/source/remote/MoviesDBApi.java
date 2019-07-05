package co.cdmunoz.themoviedb.data.source.remote;

import co.cdmunoz.themoviedb.data.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesDBApi {

    @GET("discover/movie")
    Call<MoviesResponse> getMovies(@Query("year") String movieYear, @Query("api_key") String api_key);
}
