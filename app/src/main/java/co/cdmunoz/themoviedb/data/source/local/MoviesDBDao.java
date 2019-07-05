package co.cdmunoz.themoviedb.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import co.cdmunoz.themoviedb.data.MovieItem;


@Dao
public interface MoviesDBDao {

    @Query("SELECT * FROM movies ORDER BY vote_average DESC")
    LiveData<List<MovieItem>> queryMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieItem movieItem);

    @Query("DELETE FROM movies")
    void deleteAllMovies();
}

