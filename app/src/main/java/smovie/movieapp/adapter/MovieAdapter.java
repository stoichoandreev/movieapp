package smovie.movieapp.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import smovie.movieapp.BR;
import smovie.movieapp.R;
import smovie.movieapp.api.pojos.MovieData;

/**
 * Created by sniper on 12/6/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingMovieViewHolder> {

    private List<MovieData> mMovies = new ArrayList<>();

    public MovieAdapter(@NonNull List<MovieData> movies) {
        super();
        this.mMovies.addAll(movies);
    }

    @Override
    public BindingMovieViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie_result, parent, false);
        BindingMovieViewHolder holder = new BindingMovieViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingMovieViewHolder holder, int position) {
        final MovieData movie = mMovies.get(position);
        holder.getBinding().setVariable(BR.movie, movie);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
    public void setMovies(List<MovieData> movies){
        this.mMovies.clear();
        if(movies != null) this.mMovies.addAll(movies);
        notifyDataSetChanged();
    }
    public static class BindingMovieViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding viewHolderBinding;

        public BindingMovieViewHolder(View v) {
            super(v);
            viewHolderBinding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return viewHolderBinding;
        }
    }
    public MovieData getMovie(int position){
        return (position >= 0 && position < mMovies.size()) ? mMovies.get(position) : null;
    }
}
