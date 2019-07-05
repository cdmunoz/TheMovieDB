package co.cdmunoz.themoviedb;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import co.cdmunoz.themoviedb.data.MovieItem;
import co.cdmunoz.themoviedb.data.source.local.MoviesDBDao;
import co.cdmunoz.themoviedb.data.source.local.MoviesDBDatabase;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MoviesDBDaoTest {

    private MoviesDBDatabase moviesDBDatabase;
    private MoviesDBDao moviesDBDao;

    @Before
    public void initDB() {
        //use in memory db. The data will be deleted after the process is killed
        moviesDBDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), MoviesDBDatabase.class)
                .allowMainThreadQueries()//only for testing purposes
                .build();
        moviesDBDao = moviesDBDatabase.moviesDBDao();
    }

    @After
    public void closeDB() {
        moviesDBDatabase.close();
    }

    @Test
    public void getMovies_WhenNoMoviesInserted() throws InterruptedException {
        List<MovieItem> movies = getValue(moviesDBDao.queryMovies());
        assertEquals(0, movies.size());
    }

    @Test
    public void insertMovie_SuccessfullyInsertMovie() throws InterruptedException {
        MovieItem movieItem = populateMovieItemWithData();
        moviesDBDao.insertMovie(movieItem);
        List<MovieItem> movies = getValue(moviesDBDao.queryMovies());
        assertEquals(1, movies.size());
        assertEquals(movies.get(0).getId(), movieItem.getId());
        moviesDBDao.deleteAllMovies();//to allow running new tests with the initial conditions
    }

    private MovieItem populateMovieItemWithData() {
        List<Integer> genreIds = new ArrayList();
        genreIds.add(1);
        genreIds.add(2);
        genreIds.add(3);
        return new MovieItem(1, "Overview", "EN", "Title", false, "Title",
                genreIds, "path_poster", "back_path", "2018-09-21", 8.0,
                123.0, false, 8.0);
    }

    public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);

        return (T) data[0];
    }
}
