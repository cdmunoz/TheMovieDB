package co.cdmunoz.themoviedb.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.cdmunoz.themoviedb.BuildConfig;
import co.cdmunoz.themoviedb.R;
import co.cdmunoz.themoviedb.data.MovieItem;

public class MoviesDBAdapter extends RecyclerView.Adapter<MoviesDBAdapter.MoviesDBViewHolder> {

    private List<MovieItem> moviesDbList;

    public MoviesDBAdapter(List<MovieItem> movies) {
        moviesDbList = movies;
    }

    @NonNull
    @Override
    public MoviesDBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MoviesDBViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesDBAdapter.MoviesDBViewHolder holder, int position) {
        MovieItem movieDBItem = moviesDbList.get(position);
        holder.movieDbItem(movieDBItem);
    }

    @Override
    public int getItemCount() {
        return moviesDbList.size();
    }

    class MoviesDBViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImagePoster;
        TextView movieTitle;
        TextView movieReleaseDate;
        TextView movieVoteAvg;

        MoviesDBViewHolder(final View itemView) {
            super(itemView);
            movieImagePoster = itemView.findViewById(R.id.movie_image_poster);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieReleaseDate = itemView.findViewById(R.id.movie_release_date);
            movieVoteAvg = itemView.findViewById(R.id.movie_vote_avg);
        }

        void movieDbItem(MovieItem resultsItem) {
            Picasso.with(itemView.getContext())
                    .load(BuildConfig.IMG_BASE_URL + resultsItem.getPosterPath())
                    .into(movieImagePoster);
            movieTitle.setText(resultsItem.getTitle());
            movieReleaseDate.setText(resultsItem.getReleaseDate());
            movieVoteAvg.setText(String.format("%s", resultsItem.getVoteAverage().toString()));
        }
    }
}
