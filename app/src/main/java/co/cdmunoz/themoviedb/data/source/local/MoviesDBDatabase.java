package co.cdmunoz.themoviedb.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import co.cdmunoz.themoviedb.data.MovieItem;
import co.cdmunoz.themoviedb.data.source.MoviesDBDataConverter;


@Database(entities = {MovieItem.class}, version = 1)
@TypeConverters(MoviesDBDataConverter.class)
public abstract class MoviesDBDatabase extends RoomDatabase {

    private static MoviesDBDatabase INSTANCE;

    public static MoviesDBDatabase getMoviesDbDatabase(Context context) {
        if (null == INSTANCE) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MoviesDBDatabase.class, "movies_db.db").build();
        }
        return INSTANCE;
    }

    public abstract MoviesDBDao moviesDBDao();
}
