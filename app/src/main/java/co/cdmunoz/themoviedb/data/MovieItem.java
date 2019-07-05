package co.cdmunoz.themoviedb.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "movies")
public class MovieItem implements Parcelable {
    @Json(name = "id")
    @PrimaryKey
    private Integer id;
    @Json(name = "overview")
    private String overview;
    @Json(name = "original_language")
    @ColumnInfo(name = "original_language")
    private String originalLanguage;
    @Json(name = "original_title")
    @ColumnInfo(name = "original_title")
    private String originalTitle;
    @Json(name = "video")
    private Boolean video;
    @Json(name = "title")
    private String title;
    @Json(name = "genre_ids")
    private List<Integer> genreIds;
    @Json(name = "poster_path")
    @ColumnInfo(name = "poster_path")
    private String posterPath;
    @Json(name = "backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;
    @Json(name = "release_date")
    @ColumnInfo(name = "release_date")
    private String releaseDate;
    @Json(name = "vote_average")
    @ColumnInfo(name = "vote_average")
    private Double voteAverage;
    @Json(name = "popularity")
    private Double popularity;
    @Json(name = "adult")
    private Boolean adult;
    @Json(name = "vote_count")
    @ColumnInfo(name = "vote_count")
    private Double voteCount;

    public MovieItem(Integer id, String overview, String originalLanguage, String originalTitle, Boolean video, String title, List<Integer> genreIds, String posterPath, String backdropPath, String releaseDate, Double voteAverage, Double popularity, Boolean adult, Double voteCount) {
        this.id = id;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.video = video;
        this.title = title;
        this.genreIds = genreIds;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.adult = adult;
        this.voteCount = voteCount;
    }

    public MovieItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Double getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Double voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.overview);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeValue(this.video);
        dest.writeString(this.title);
        dest.writeList(this.genreIds);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.popularity);
        dest.writeValue(this.adult);
        dest.writeValue(this.voteCount);
    }

    protected MovieItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.title = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteCount = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}
