package co.cdmunoz.themoviedb.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.util.List;

public class MoviesResponse implements Parcelable {
    @Json(name = "page")
    private Integer page;
    @Json(name = "total_pages")
    private Integer totalPages;
    @Json(name = "results")
    private List<MovieItem> results;
    @Json(name = "total_results")
    private Integer totalResults;

    public MoviesResponse(Integer page, Integer totalPages, List<MovieItem> results, Integer totalResults) {
        this.page = page;
        this.totalPages = totalPages;
        this.results = results;
        this.totalResults = totalResults;
    }

    public MoviesResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieItem> getResults() {
        return results;
    }

    public void setResults(List<MovieItem> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.page);
        dest.writeValue(this.totalPages);
        dest.writeTypedList(this.results);
        dest.writeValue(this.totalResults);
    }

    protected MoviesResponse(Parcel in) {
        this.page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalPages = (Integer) in.readValue(Integer.class.getClassLoader());
        this.results = in.createTypedArrayList(MovieItem.CREATOR);
        this.totalResults = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<MoviesResponse> CREATOR = new Creator<MoviesResponse>() {
        @Override
        public MoviesResponse createFromParcel(Parcel source) {
            return new MoviesResponse(source);
        }

        @Override
        public MoviesResponse[] newArray(int size) {
            return new MoviesResponse[size];
        }
    };
}
