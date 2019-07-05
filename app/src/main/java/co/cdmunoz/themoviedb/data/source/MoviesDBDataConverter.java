package co.cdmunoz.themoviedb.data.source;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class MoviesDBDataConverter {

    @TypeConverter
    public String fromListToGenres(List<Integer> genres) {
        if (genres == null) return "";
        StringBuilder commaSepValueBuilder = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            commaSepValueBuilder.append(genres.get(i));
            if (i != genres.size() - 1) {
                commaSepValueBuilder.append(", ");
            }
        }
        return commaSepValueBuilder.toString();
    }

    @TypeConverter
    public List<Integer> fromStringToList(String genres) {
        if (genres == null) return new ArrayList();
        List<Integer> list = new ArrayList<>();
        genres = genres.replace(" ", "");
        for (String s : genres.split(","))
            list.add(Integer.parseInt(s));
        return list;
    }
}
