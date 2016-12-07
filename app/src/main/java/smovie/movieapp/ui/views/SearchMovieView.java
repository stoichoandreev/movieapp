package smovie.movieapp.ui.views;

import smovie.movieapp.api.pojos.MovieSearchQueryParseData;

/**
 * Created by sniper on 12/6/16.
 */

public interface SearchMovieView extends BaseView{
    void onMoviesResultReady(MovieSearchQueryParseData moviesSearchResult);
    void openDetailsScreen(int position);
    void setIsResuming(boolean isResume);
    boolean isResuming();
}
